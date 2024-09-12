package com.example.manage.test;

import lombok.*;

/**
 * 파일명: TestRequest.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-12  ksj         신규 생성
 * </pre>
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestRequest {
    private String value1;
    private int value2;
    private int value3;
    private boolean value4;
}
