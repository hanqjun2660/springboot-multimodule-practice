package com.example.user.controller;

import com.example.core.common.CommonResponse;
import com.example.core.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 파일명: UserController.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-23  ksj         신규 생성
 * </pre>
 */

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class UserController {

    private final JWTUtil jwtUtil;

    @GetMapping("/myInfo")
    public CommonResponse myInfo(HttpServletRequest request) {
        Optional<String> authorizationHeader = Optional.ofNullable(request.getHeader("Authorization"));

        if(!authorizationHeader.isPresent()) {
            log.info("요청에 Authorization Header가 누락됨");
            return CommonResponse.ERROR(null);
        }

        String accessToken = authorizationHeader.get();
        if(accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.replaceFirst("Bearer ", "");
        }

        Long userNo = jwtUtil.getUserNo(accessToken);

        return CommonResponse.OK("success", userNo);
    }
}
