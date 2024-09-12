package com.example.core.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * 파일명: JWTFilter.java
 * 설명:
 * 버전: 1.0
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 2024-09-11  ksj         신규 생성
 * </pre>
 */

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청 헤더 검증
        Optional<String> authorizationHeader = Optional.ofNullable(request.getHeader("Authorization"));

        if(!authorizationHeader.isPresent()) {
            log.info("요청에 Authorization Header가 누락됨");
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer 제거
        String accessToken = authorizationHeader.get();
        if(accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.replaceFirst("Bearer ", "");
        }

        // accessToken인지 RefreshToken인지 검증
        if(!jwtUtil.getCategory(accessToken).equals("access")) {
            log.info("accessToken이 아님");
            httpResponse(JWTMessage.INVALID.getMessage(), JWTMessage.INVALID.getStatusCode(), response);
            return;
        }

        // 토큰 만료 확인
        if(jwtUtil.isExpired(accessToken)) {
            log.info("accessToken의 유효기간이 만료됨");
            httpResponse(JWTMessage.EXPIRED.getMessage(), JWTMessage.INVALID.getStatusCode(), response);
            return;
        }

        Long memberNo = jwtUtil.getUserNo(accessToken);
        String role = jwtUtil.getRole(accessToken);

        UserDetail userDetail = new UserDetail(memberNo, role);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    /**
     * jwt 검증용 응답
     * @param message
     * @param statusCode
     */
    private void httpResponse(String message, int statusCode, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(message);
        response.setStatus(statusCode);
    }

    /**
     * 임시 클래스
     */
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserDetail implements UserDetails {

        private Long memberNo;
        private String role;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> grantedRole = new ArrayList<>();

            grantedRole.add((GrantedAuthority) () -> this.role);

            return grantedRole;
        }

        @Override
        public String getPassword() {
            return "1q2w3e4r";
        }

        @Override
        public String getUsername() {
            return "임시유저아이디";
        }
    }
}
