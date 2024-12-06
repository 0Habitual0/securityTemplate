package com.habitual.demo.touristRoutes.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TouristRoutesPageDto {

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

    private int offset;

}
