package com.ryan.redlight.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 提供分页所需部分工具，本项目分页主要使用PageHelper
 */
public class PageUtil {
    /**
     * 负责VO对象的PageInfo转换
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
