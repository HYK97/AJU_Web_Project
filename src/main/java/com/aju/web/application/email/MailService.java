package com.aju.web.application.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aju.web.domain.email.EmailConsultation;
import com.aju.web.infra.repository.email.EmailConsultationRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${spring.mail.mail-id}")
    String authorEmail;
    private final JavaMailSender mailSender;
    private final EmailConsultationRepository emailConsultationRepository;

    @Transactional
    public void sendMail(EmailConsultation emailConsultation) throws MessagingException {

        MimeMessage message = null;

        StringBuilder sb = new StringBuilder();
        sb.append("<h1>예약 상담 메일</h1>");
        sb.append("예약자 성함 : ").append(emailConsultation.getAuthor()).append("<br>");
        sb.append("예약자 이메일 : ").append(emailConsultation.getEmail()).append("<br>");
        sb.append("예약자 연락처 : ").append(emailConsultation.getPhone()).append("<br>");
        sb.append("예약자 상담내용 : ").append(emailConsultation.getContent()).append("<br>");

        message = mailSender.createMimeMessage();
        MimeMessageHelper mailHelper = new MimeMessageHelper(message, "UTF-8");
        mailHelper.setFrom(authorEmail);
        mailHelper.setTo(authorEmail);
        mailHelper.setSubject("예약 상담 메일 입니다.");
        mailHelper.setText(sb.toString(), true);
        mailSender.send(message);

        mailHelper.setFrom(authorEmail);
        mailHelper.setTo(emailConsultation.getEmail());
        mailHelper.setSubject("상담 예약이 완료되었습니다.");
        mailHelper.setText("빠른 시일내에 연락드리겠습니다.", true);
        mailSender.send(message);

        emailConsultationRepository.save(emailConsultation);

    }

}
