package com.fluxpay.common.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 通用分页响应 VO
 *
 * @param <T> 列表元素类型
 */
@Data
public class PageVO<T> {

    /** 当前页数据 */
    private List<T> records;

    /** 总记录数 */
    private long total;

    /** 当前页码 */
    private int page;

    /** 每页条数 */
    private int size;

    /** 总页数 */
    private int pages;

    public static <T> PageVO<T> of(List<T> records, long total, int page, int size) {
        PageVO<T> vo = new PageVO<>();
        vo.records = records;
        vo.total = total;
        vo.page = page;
        vo.size = size;
        vo.pages = size == 0 ? 0 : (int) Math.ceil((double) total / size);
        return vo;
    }

    public static <T> PageVO<T> empty(int page, int size) {
        return of(Collections.emptyList(), 0, page, size);
    }
}
