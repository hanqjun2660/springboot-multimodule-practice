package com.example.core.common;

import lombok.Getter;
import org.springframework.util.ObjectUtils;

/**
 * 파일명: CommonResponse.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-09  ksj         신규 생성
 * </pre>
 */

@Getter
public class CommonResponse<T> {

    private String statusCode;
    private String message;
    private T data;

    private CommonResponse(CommonResponseCode code, String message, T data) {
        this.statusCode = code.getCode();
        this.message = ObjectUtils.isEmpty(message) ? code.getDefaultMessage() : message;
        this.data = data;
    }

    public static <T> CommonResponse<T> OK(String message, T data) {
        return new CommonResponse<T>(CommonResponseCode.OK, message, data);
    }

    public static <T> CommonResponse<T> OK(CommonResponseCode statusCode, String message, T data) {
        return new CommonResponse<T>(statusCode, message, data);
    }

    public static <T> CommonResponse<T> ERROR(String message, T data) {
        return new CommonResponse<T>(CommonResponseCode.ERROR, message, data);
    }

    /*Over Loading*/
    public static <T> CommonResponse<T> ERROR(CommonResponseCode statusCode, String message, T data) {
        return new CommonResponse<T>(statusCode, message, data);
    }

    public static <T> CommonResponse<T> OK(T data) {
        return OK(null, data);
    }

    public static CommonResponse OK(String message) {
        return OK(message, null);
    }

    public static CommonResponse OK(CommonResponseCode statusCode, String message) {
        return OK(statusCode, message, null);
    }

    public static CommonResponse ERROR(String message) {
        return ERROR(message, null);
    }

    public static <T> CommonResponse<T> ERROR(T data) {
        return ERROR(null, data);
    }

    public static CommonResponse ERROR(CommonResponseCode statusCode, String message) {
        return new CommonResponse(statusCode, message, null);
    }
}
