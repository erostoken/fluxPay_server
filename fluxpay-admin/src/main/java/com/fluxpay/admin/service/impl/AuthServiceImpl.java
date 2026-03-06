package com.fluxpay.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fluxpay.admin.config.JwtConfig;
import com.fluxpay.admin.dto.auth.LoginReqDTO;
import com.fluxpay.admin.dto.auth.LoginRespVO;
import com.fluxpay.admin.dto.auth.UserInfoVO;
import com.fluxpay.admin.security.JwtAuthFilter;
import com.fluxpay.admin.security.JwtUtils;
import com.fluxpay.admin.service.AuthService;
import com.fluxpay.common.exception.BusinessException;
import com.fluxpay.common.result.ResultCode;
import com.fluxpay.core.orm.sys.entity.SysUser;
import com.fluxpay.core.orm.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SysUserService      sysUserService;
    private final JwtUtils            jwtUtils;
    private final JwtConfig           jwtConfig;
    private final PasswordEncoder     passwordEncoder;
    private final StringRedisTemplate redisTemplate;

    @Override
    public LoginRespVO login(LoginReqDTO req) {
        // 1. 查用户
        SysUser user = sysUserService.getOne(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUsername, req.getUsername())
                        .eq(SysUser::getIsDeleted, 0)
        );
        if (user == null) {
            throw new BusinessException(ResultCode.LOGIN_FAILED);
        }

        // 2. 验密码
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BusinessException(ResultCode.LOGIN_FAILED);
        }

        // 3. 账号状态
        if (user.getStatus() == 0) {
            throw new BusinessException(ResultCode.ACCOUNT_DISABLED);
        }

        // 4. 更新最后登录时间（不更新 updatedTime，避免干扰业务审计）
        SysUser update = new SysUser();
        update.setId(user.getId());
        update.setLastLoginTime(new Date());
        sysUserService.updateById(update);

        // 5. 生成 Token
        String accessToken  = jwtUtils.generateAccessToken(user.getId(), user.getUsername());
        String refreshToken = jwtUtils.generateRefreshToken(user.getId(), user.getUsername());

        log.info("Admin 登录成功: username={}, id={}", user.getUsername(), user.getId());

        return LoginRespVO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(jwtConfig.getAccessTokenExpire())
                .build();
    }

    @Override
    public void logout(String token) {
        if (token == null) return;
        long remainingMs = jwtUtils.getRemainingMs(token);
        if (remainingMs > 0) {
            // 将 Token 写入 Redis 黑名单，TTL = token 剩余有效期
            redisTemplate.opsForValue().set(
                    JwtAuthFilter.BLACKLIST_KEY + token,
                    "1",
                    remainingMs,
                    TimeUnit.MILLISECONDS
            );
        }
        log.info("Admin 登出，Token 已加入黑名单");
    }

    @Override
    public String refreshToken(String refreshToken) {
        if (!jwtUtils.isValid(refreshToken)) {
            throw new BusinessException(ResultCode.TOKEN_INVALID);
        }
        Long   userId   = jwtUtils.getUserId(refreshToken);
        String username = jwtUtils.getUsername(refreshToken);
        return jwtUtils.generateAccessToken(userId, username);
    }
}
