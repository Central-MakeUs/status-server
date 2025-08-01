package com.statoverflow.status.domain.attribute.service;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.attribute.dto.AttributesReturnDto;
import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.attribute.repository.UsersAttributeLogRepository;
import com.statoverflow.status.domain.attribute.repository.UsersAttributeProgressRepository;
import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.entity.UsersAttributeLog;
import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;
import com.statoverflow.status.domain.users.enums.SourceType;
import com.statoverflow.status.global.event.UsersAttributeProgressEvent;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final UsersAttributeProgressRepository usersAttributeProgressRepository;
    private final UsersAttributeLogRepository usersAttributeLogRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<AttributesReturnDto> getAttributes(Long userId) {
        List<UsersAttributeProgress> attributes = usersAttributeProgressRepository.findByUserIdOrderByAttributeId(userId);

        return attributes.stream()
                .map(AttributesReturnDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void addExp(Users user, List<AttributeDto> attributes, SourceType sourceType) {

        // 경험치 로그
        attributes.forEach(attribute -> {
            usersAttributeLogRepository.save(
                UsersAttributeLog.builder()
                    .user(user)
                    .attribute(attributeRepository.findById(attribute.id()).orElseThrow())
                    .exp(attribute.exp())
                    .sourceType(sourceType)
                    .build());
        });

        // 경험치 progress
        List<UsersAttributeProgress> progresses = attributes.stream()
            .map(attribute -> {
                UsersAttributeProgress attributeProgress = usersAttributeProgressRepository.findByUserIdAndAttributeId(user.getId(), attribute.id());
                attributeProgress.setExp(attributeProgress.getExp() + attribute.exp());
                usersAttributeProgressRepository.save(attributeProgress);
                return attributeProgress;

            }).toList();

        // 레벨 업 확인 로직
        log.debug("레벨업 확인 이벤트 발행 시작, 대상 프로그레스 수: {}", progresses.size());
        eventPublisher.publishEvent(new UsersAttributeProgressEvent(progresses));
        log.debug("레벨업 확인 이벤트 발행 완료");
    }



    public List<Attribute> toEntity(List<AttributeDto> dtos) {
        return dtos.stream().map(attributeDto -> attributeRepository.findById(attributeDto.id()).orElseThrow()).toList();
    }

}
