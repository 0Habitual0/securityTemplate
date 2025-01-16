package com.yt.YcTravelManageSystem.order.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class MainPageDto {

    private Long scenicSpotNum;

    private Long touristRoutesNum;

    private Long totalNum;

    private Integer backNum;

    private Map<Date, Integer> scenicSpotWeek;

    private Map<Date, Integer> touristRoutesWeek;

}
