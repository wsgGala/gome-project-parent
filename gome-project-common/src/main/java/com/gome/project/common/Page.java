package com.gome.project.common;

import java.util.List;

/**
 * Created by qiaowentao on 2016/8/19.
 */
public class Page<T> {

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 每页显示的数量
     */
    private Integer pageSize;

    /**
     *总记录数
     */
    private Integer totalResult;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     *每页显示的内容
     */
    private List<T> data;

    /**
     *
     */
    private T condations;

}
