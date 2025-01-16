package com.yt.YcTravelManageSystem.user.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoDto {

    private String avatar;

    private String name;

    private List<String> roles;

}
