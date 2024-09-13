package com.example.manage.test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 파일명: TestEntity.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-13  ksj         신규 생성
 * </pre>
 */

@Entity
public class TestEntity {

    @Id
    private String value1;
    private int value2;
    private int value3;
    private boolean value4;
}
