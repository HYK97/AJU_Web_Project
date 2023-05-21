package com.aju.web.infra.exception;

import static com.aju.web.infra.exception.ErrorCode.*;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * packageName :  com.aju.web.infra.exception
 * fileName : GlobalExceptionAdvice
 * author :  ddh96
 * date : 2023-04-26 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-26                ddh96             최초 생성
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> applicationException(ApplicationException e) {
        log.error("exception ", e.getRootCase());
        return ResponseEntity.status(e.getErrorCode().getHttpStatusCode()).body(e.getErrorMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> applicationException(MethodArgumentNotValidException e) {
        log.error("exception ", e.getCause());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Objects.requireNonNull(e.getFieldError()).getField()+" : "+e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception e) {
        log.error("exception ", e.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR.getDescription());
    }
}
