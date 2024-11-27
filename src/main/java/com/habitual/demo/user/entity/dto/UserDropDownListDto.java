package com.habitual.demo.user.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDropDownListDto {

    private Long id;

    private String username;

    private String nickName;

    public UserDropDownListDto(Long id, String username, String nickName) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
    }

}
