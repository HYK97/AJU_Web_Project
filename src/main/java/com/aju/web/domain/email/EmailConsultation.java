package com.aju.web.domain.email;

import com.aju.web.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * packageName :  com.aju.web.domain.email
 * fileName : Email
 * author :  ddh96
 * date : 2023-04-26 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-26                ddh96             최초 생성
 */
@Entity
public class EmailConsultation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_number", nullable = false)
    private Long consultationNumber;
    String email;
    String author;
    String content;
    String phone;
}
