package com.example.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 파일명: CommonResposeCode.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-09  ksj         신규 생성
 * </pre>
 */

@Getter
@AllArgsConstructor
public enum CommonResponseCode {
    OK("200", "ok"),
    ERROR("500", "error");

    private final String code;
    private final String defaultMessage;
}
