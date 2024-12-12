package com.habitual.demo.order.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class MainPageDto {

    private Integer scenicSpotNum;

    private Integer touristRoutesNum;

    private Integer totalNum;

    private Map<Date, Integer> scenicSpotWeek;

    private Map<Date, Integer> touristRoutesWeek;

}
