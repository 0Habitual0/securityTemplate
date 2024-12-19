package com.habitual.demo.user.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordDto {

    private boolean userSelect;

    private String oldPassword;

    private String newPassword;

}
