package com.ryan.redlight.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtil {
    /**
     * VO对象的 PageInfo 转换
     * @author Ryan
     */
    public static <T, R> PageInfo<R> convertPageInfo(PageInfo<T> sourcePageInfo, List<R> targetList) {
        Page<R> page = new Page<>(sourcePageInfo.getPageNum(), sourcePageInfo.getPageSize());
        page.setTotal(sourcePageInfo.getTotal());

        PageInfo<R> targetPageInfo = new PageInfo<>(page);
        targetPageInfo.setList(targetList);
        return targetPageInfo;
    }
}
