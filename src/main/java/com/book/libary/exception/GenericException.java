package com.book.libary.exception;

import com.book.libary.model.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GenericException {
    private HttpStatus httpStatus;
    private ErrorCode errorCode;
    private String errorMessage;
}
