package com.aju.web.application.admin;

import org.springframework.stereotype.Service;

import com.aju.web.domain.admin.Admin;
import com.aju.web.infra.exception.ApplicationException;
import com.aju.web.infra.exception.ErrorCode;
import com.aju.web.infra.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.application.admin
 * fileName : AdminService
 * author :  ddh96
 * date : 2023-04-27 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-27                ddh96             최초 생성
 */
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public void login(String id, String password) {
        Admin admin = adminRepository.findByAdminId(id)
            .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        if (!admin.userAuthentication(id, password)) {
            throw new ApplicationException(ErrorCode.LOGIN_ERROR);
        }
    }
}
