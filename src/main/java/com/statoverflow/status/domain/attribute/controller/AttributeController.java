package com.statoverflow.status.domain.attribute.controller;

import com.statoverflow.status.domain.attribute.dto.AttributesReturnDto;
import com.statoverflow.status.domain.attribute.service.AttributeService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.global.annotation.CurrentUser;
import com.statoverflow.status.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attribute")
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<AttributesReturnDto>>> getMyAttributes(@CurrentUser BasicUsersDto user) {
        log.info("유저 능력치 정보 요청 수신, 코드: {}", user.toString());

        return ApiResponse.ok(attributeService.getAttributes(user.id()));

    }
}
