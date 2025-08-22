package com.statoverflow.status.domain.attribute.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.attribute.dto.AttributesReturnDto;
import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.attribute.repository.UsersAttributeLogRepository;
import com.statoverflow.status.domain.attribute.repository.UsersAttributeProgressRepository;
import com.statoverflow.status.domain.master.entity.AttributeLevel;
import com.statoverflow.status.domain.master.repository.AttributeLevelRepository;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuest;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.entity.UsersAttributeLog;
import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;
import com.statoverflow.status.domain.users.enums.SourceType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final UsersAttributeProgressRepository usersAttributeProgressRepository;
    private final UsersAttributeLogRepository usersAttributeLogRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final AttributeLevelRepository attributeLevelRepository;

    @Override
    public List<AttributesReturnDto> getAttributes(Long userId) {
        List<UsersAttributeProgress> attributes = usersAttributeProgressRepository.findByUserIdOrderByAttributeId(userId);

        return attributes.stream()
            .map(attributeProgress -> {
                AttributeLevel levelInfo = attributeLevelRepository
                    .findTopByIdTypeAndXpRequiredGreaterThanOrderByXpRequiredAsc(attributeProgress.getAttribute().getType(), attributeProgress.getTotalExp());

                Long baseExp = attributeLevelRepository
                    .findByIdTypeAndIdLevel(levelInfo.getId().getType(), levelInfo.getId().getLevel()-1)
                    .map(AttributeLevel::getXpRequired)
                    .orElse(0L);

                log.debug("levelInfo : {}, baseExp : {}", levelInfo.getXpRequired(), baseExp);

                return AttributesReturnDto.getLevel(attributeProgress, levelInfo, baseExp);
            })
            .collect(Collectors.toList());
    }

    @Override
    public <T> void addExp(Users user, List<AttributeDto> attributes, T data) {

        SourceType sourceType;
        Long matchingId;
        if (data instanceof UsersSubQuest) {
            matchingId = ((UsersSubQuest)data).getId();
            sourceType = SourceType.SUBQUESTLOG;

        } else if (data instanceof UsersMainQuest) {
            matchingId = ((UsersMainQuest)data).getId();
            sourceType = SourceType.MAINQUEST;
        } else {
			matchingId = null;
			sourceType = null;
		}

		// 경험치 로그
        attributes.forEach(attribute -> {

            usersAttributeLogRepository.save(
                UsersAttributeLog.builder()
                    .user(user)
                    .attribute(attributeRepository.findById(attribute.id()).orElseThrow())
                    .matchingId(matchingId)
                    .exp(attribute.exp())
                    .sourceType(sourceType)
                    .build());

            UsersAttributeProgress attributeProgress = usersAttributeProgressRepository.findByUserIdAndAttributeId(user.getId(), attribute.id());
            attributeProgress.setTotalExp(attributeProgress.getTotalExp() + attribute.exp());

        });

        log.debug("경험치 추가 완료");
    }


}
