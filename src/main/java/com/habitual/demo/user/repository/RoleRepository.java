package com.habitual.demo.user.repository;

import com.habitual.demo.user.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 数据访问层JPA 角色
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRoleCode(String roleCode);

    @Query("SELECT r FROM RoleEntity r WHERE " +
            "(:roleCode IS NULL OR r.roleCode LIKE %:roleCode%) AND " +
            "(:roleName IS NULL OR r.roleName LIKE %:roleName%) AND " +
            "(:status IS NULL OR r.status = :status) AND" +
            "(:createBy IS NULL OR r.createBy LIKE %:createBy%) AND" +
            "(:updateBy IS NULL OR r.updateBy LIKE %:updateBy%) AND" +
            "(:remark IS NULL OR r.remark LIKE %:remark%)")
    Page<RoleEntity> findByCriteria(@Param("roleCode") String roleCode,
                                    @Param("roleName") String roleName,
                                    @Param("status") Long status,
                                    @Param("createBy") String createBy,
                                    @Param("updateBy") String updateBy,
                                    @Param("remark") String remark,
                                    Pageable pageable);

}
