package com.habitual.demo.user.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePageDto {

    private String roleCode;

    private String roleName;

    private Long status;

    private String createBy;

    private String updateBy;

    private String remark;

    private int pageNum;

    private int pageSize;

}
