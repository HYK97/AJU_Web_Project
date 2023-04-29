package com.aju.web.persentation.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aju.web.application.admin.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.admin
 * fileName : AdminController
 * author :  ddh96
 * date : 2023-04-26 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-26                ddh96             최초 생성
 */
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final AdminService adminService;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<Object> loginProcess(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {
        adminService.login(loginRequest.getId(), loginRequest.getPassword());
        session.setAttribute("admin", true);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("admin") != null) {
            return "redirect:/";
        }
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
