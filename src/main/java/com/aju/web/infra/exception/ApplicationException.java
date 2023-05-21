package com.aju.web.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;
    private Exception rootCase;

    public ApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
    public ApplicationException(ErrorCode errorCode,Exception rootCase) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
        this.rootCase = rootCase;
    }
}