package com.aju.web.domain.admin;

import com.aju.web.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * packageName :  com.aju.web.domain.admin
 * fileName : Admin
 * author :  ddh96
 * date : 2023-04-25 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-25                ddh96             최초 생성
 */
@Entity
public class Admin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_number", nullable = false)
    private Long adminNumber;
    private String adminId;
    private String adminPassword;

    public boolean userAuthentication(String adminId, String password) {
        return this.adminId.equals(adminId) && this.adminPassword.equals(password);
    }

}
