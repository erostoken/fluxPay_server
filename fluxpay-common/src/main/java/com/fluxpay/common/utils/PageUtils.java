package com.fluxpay.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fluxpay.common.dto.PageVO;

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
}
