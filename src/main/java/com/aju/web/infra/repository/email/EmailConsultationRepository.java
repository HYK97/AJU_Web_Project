package com.aju.web.infra.repository.email;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aju.web.domain.email.EmailConsultation;

/**
 * packageName :  com.aju.web.infra.repository.email
 * fileName : EmailConsultationRepository
 * author :  ddh96
 * date : 2023-05-03 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-05-03                ddh96             최초 생성
 */
public interface EmailConsultationRepository extends JpaRepository<EmailConsultation,Long> {
}
