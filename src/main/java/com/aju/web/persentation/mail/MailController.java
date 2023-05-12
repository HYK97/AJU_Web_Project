package com.aju.web.persentation.mail;

import static com.aju.web.infra.exception.ErrorCode.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import com.aju.web.application.email.MailService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.aju.web.persentation.mail
 * fileName : MailController
 * author :  ddh96
 * date : 2023-05-03
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-05-03                ddh96             최초 생성
 */
@Controller
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/mail")
    public ResponseEntity<String> mail(@Valid MailRequest mailRequest) throws MessagingException {
        mailService.sendMail(MailRequest.from(mailRequest));
        return ResponseEntity.ok().body("OK");
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<Object> exception(MessagingException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR.getDescription());
    }

}
