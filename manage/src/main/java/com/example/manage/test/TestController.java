package com.example.manage.test;

import com.example.core.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 파일명: TestController.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-12  ksj         신규 생성
 * </pre>
 */

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TestController {

    @PostMapping("/test1")
    public CommonResponse testApi(@RequestBody TestRequest request) {
        log.info("controller......", request.toString());
        return CommonResponse.OK("success");
    }
}
