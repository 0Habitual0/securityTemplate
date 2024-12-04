package com.habitual.demo.carouselImage.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarouselImagePageDto {

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

    private int offset;

}
