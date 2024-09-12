package com.example.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 파일명: GlobalExcptionHandler.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-12  ksj         신규 생성
 * </pre>
 */

@Slf4j
@RestControllerAdvice
public class GlobalExcptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        log.error("Exception : ", e);
    }
}
