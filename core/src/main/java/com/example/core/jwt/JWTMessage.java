package com.example.core.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 파일명: JWTMessage.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-12  ksj         신규 생성
 * </pre>
 */

@Getter
@AllArgsConstructor
public enum JWTMessage {

    INVALID("Token 형식이 잘못됨", 400),
    EXPIRED("만료된 Token", 403)
    ;

    private String message;
    private int statusCode;
}
