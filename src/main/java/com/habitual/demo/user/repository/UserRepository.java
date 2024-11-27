package com.habitual.demo.user.repository;

import com.habitual.demo.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 数据访问层JPA 用户
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPassword(String userName, String password);

    UserEntity findByUsername(String userName);

    @Query("SELECT u FROM UserEntity u WHERE " +
            "(:username IS NULL OR u.username LIKE %:username%) AND " +
            "(:nickname IS NULL OR u.nickname LIKE %:nickname%) AND " +
            "(:sex IS NULL OR u.sex = :sex) AND " +
            "(:age IS NULL OR u.age = :age) AND " +
            "(:email IS NULL OR u.email LIKE %:email%) AND " +
            "(:tel IS NULL OR u.tel LIKE %:tel%) AND " +
            "(:role IS NULL OR u.role = :role) AND " +
            "(:status IS NULL OR u.status = :status) AND" +
            "(:createBy IS NULL OR u.createBy LIKE %:createBy%) AND" +
            "(:updateBy IS NULL OR u.updateBy LIKE %:updateBy%) AND" +
            "(:remark IS NULL OR u.remark LIKE %:remark%)")
    Page<UserEntity> findByCriteria(@Param("username") String username,
                                    @Param("nickname") String nickname,
                                    @Param("sex") String sex,
                                    @Param("age") Long age,
                                    @Param("email") String email,
                                    @Param("tel") String tel,
                                    @Param("role") String role,
                                    @Param("status") Long status,
                                    @Param("createBy") String createBy,
                                    @Param("updateBy") String updateBy,
                                    @Param("remark") String remark,
                                    Pageable pageable);

}
