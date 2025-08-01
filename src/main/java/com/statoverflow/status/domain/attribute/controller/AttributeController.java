package com.statoverflow.status.domain.attribute.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.statoverflow.status.domain.attribute.dto.AttributesReturnDto;
import com.statoverflow.status.domain.attribute.service.AttributeService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.global.annotation.CurrentUser;
import com.statoverflow.status.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attribute")
@Tag(name = "[능력치] Attribute API", description = "사용자 능력치 관련 API")
public class AttributeController {

    private final AttributeService attributeService;

    @Operation(
        summary = "내 능력치 레벨 및 경험치 조회",
        description = "현재 로그인한 사용자의 모든 능력치 레벨 및 경험치 정보를 조회합니다."
    )
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<AttributesReturnDto>>> getMyAttributes(@CurrentUser BasicUsersDto user) {
        log.info("유저 능력치 정보 요청 수신, 코드: {}", user.toString());
        return ApiResponse.ok(attributeService.getAttributes(user.id()));
    }
}