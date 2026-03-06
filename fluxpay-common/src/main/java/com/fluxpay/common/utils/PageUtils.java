package com.fluxpay.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fluxpay.common.dto.PageVO;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页工具类
 *
 * <p>统一将 MyBatis-Plus 的 {@link IPage} 转换为 {@link PageVO}，
 * 避免各模块重复编写转换逻辑。
 */
public class PageUtils {

    private PageUtils() {
    }

    /**
     * IPage → PageVO 转换（泛型一致时直接使用）
     */
    public static <T> PageVO<T> toVO(IPage<T> page) {
        return PageVO.of(
                page.getRecords(),
                page.getTotal(),
                (int) page.getCurrent(),
                (int) page.getSize()
        );
    }

    /**
     * IPage&lt;S&gt; → PageVO&lt;T&gt; 转换（附带类型映射函数）
     *
     * @param page    MyBatis-Plus 分页结果
     * @param mapper  实体 → VO 转换函数
     */
    public static <S, T> PageVO<T> toVO(IPage<S> page, Function<S, T> mapper) {
        return PageVO.of(
                page.getRecords().stream().map(mapper).collect(Collectors.toList()),
                page.getTotal(),
                (int) page.getCurrent(),
                (int) page.getSize()
        );
    }
}
