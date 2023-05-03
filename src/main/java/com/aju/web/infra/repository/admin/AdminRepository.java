package com.aju.web.infra.repository.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aju.web.domain.admin.Admin;

/**
 * packageName :  com.aju.web.infra.repository
 * fileName : AdminRepository
 * author :  ddh96
 * date : 2023-04-27 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-27                ddh96             최초 생성
 */

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByAdminId(String adminId);
}
