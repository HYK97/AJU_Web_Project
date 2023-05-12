package com.aju.web.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("사용자가 없습니다.", HttpStatus.BAD_REQUEST),
    IMAGE_SAVE_ERROR("사진 저장 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    LOGIN_ERROR("잘못된 로그인 정보입니다.", HttpStatus.BAD_REQUEST),
    NOTICE_NOT_FOUND("공지사항이 없습니다.", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_FOUND("없는 이미지입니다.", HttpStatus.BAD_REQUEST),
    FILE_NOT_FOUND("없는 파일입니다.", HttpStatus.BAD_REQUEST),
    FILE_SAVE_ERROR("파일 저장 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_SIZE_EXCEED("파일 사이즈가 초과되었습니다.", HttpStatus.BAD_REQUEST),
    FILE_EXTENSION_DENIED("파일 확장자가 허용되지 않습니다.", HttpStatus.BAD_REQUEST),
    FILE_DELETE_ERROR("파일 삭제 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_DELETE_ERROR("이미지 삭제 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED );
    private final String description;
    private final HttpStatusCode httpStatusCode;
}