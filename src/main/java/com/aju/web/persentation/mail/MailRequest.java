package com.aju.web.persentation.mail;

import org.hibernate.validator.constraints.Length;

import com.aju.web.domain.email.EmailConsultation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName :  com.aju.web.persentation.mail
 * fileName : Mail
 * author :  ddh96
 * date : 2023-05-03 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-05-03                ddh96             최초 생성
 */
@Getter
@AllArgsConstructor
public class MailRequest {
    @Email
    @NotEmpty
    @Length(min = 4,max = 100)
    private String email;
    @NotEmpty
    @Length(min = 1,max = 10)
    private String name;
    @NotEmpty
    @Length(min = 1)
    private String message;
    @NotEmpty
    @Length(min = 5, max = 16)
    private String phone;

    public static EmailConsultation from(MailRequest request) {
        return EmailConsultation.builder()
            .email(request.getEmail())
            .author(request.getName())
            .content(request.getMessage())
            .phone(request.getPhone())
            .build();
    }
}
