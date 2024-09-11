package com.example.managementapi;

import com.example.core.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.core", "com.example.managementapi"})
@RequiredArgsConstructor
public class ManagementApiApplication implements CommandLineRunner {

    private final JWTUtil jwtUtil;

    public static void main(String[] args) {
        SpringApplication.run(ManagementApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(jwtUtil.createToken("access", 1L, "admin", 1800000L));
    }
}
