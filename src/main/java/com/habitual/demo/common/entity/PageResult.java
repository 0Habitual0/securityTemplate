package com.habitual.demo.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {

    private List<T> data;
    private int totalCount;
    private int pages;

}
