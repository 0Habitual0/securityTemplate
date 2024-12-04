package com.habitual.demo.scenicSpot.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScenicSpotPageDto {

    private String name;

    private String type;

    private String level;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

    private int offset;

}

