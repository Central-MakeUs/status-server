package com.statoverflow.status.global.event;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.statoverflow.status.domain.attribute.repository.UsersAttributeProgressRepository;
import com.statoverflow.status.domain.master.entity.AttributeLevel;
import com.statoverflow.status.domain.master.entity.AttributeLevelId;
import com.statoverflow.status.domain.master.enums.AttributeType;
import com.statoverflow.status.domain.master.repository.AttributeLevelRepository;
import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class LevelUpListener {

	private final AttributeLevelRepository attributeLevelRepository;
	private final UsersAttributeProgressRepository usersAttributeProgressRepository;

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void handleExpUpdateAndLevelUp(UsersAttributeProgressEvent event) {
		List<UsersAttributeProgress> progressList = event.progresses();
		log.debug("handleExpUpdateAndLevelUp 이벤트 발생: 처리할 항목 {}개", progressList.size());

		// 1. 마스터 데이터를 한 번에 조회
		Map<AttributeLevelId, AttributeLevel> levelCache = preloadAttributeLevels();

		// 2. 각 진행사항에 대해 레벨업 처리
		progressList.forEach(progress -> processLevelUp(progress, levelCache));
	}

	private void processLevelUp(UsersAttributeProgress originProgress, Map<AttributeLevelId, AttributeLevel> levelCache) {
		AttributeType type = originProgress.getAttribute().getType();
		int currentLevel = originProgress.getLevel();
		int currentExp = originProgress.getExp();

		UsersAttributeProgress progress = usersAttributeProgressRepository.save(originProgress);

		while (true) {
			AttributeLevelId nextLevelId = new AttributeLevelId(type, currentLevel + 1);
			Optional<Integer> nextRequiredExp = getRequiredExp(nextLevelId, levelCache);

			// 다음 레벨업 정보가 없거나, 경험치가 부족하면 루프 종료
			if (nextRequiredExp.isEmpty() || currentExp < nextRequiredExp.get()) {
				break;
			}

			// 레벨업 처리
			currentLevel++;
			currentExp -= nextRequiredExp.get();

			log.debug("사용자 '{}'의 '{}' 레벨이 {}로 상승, 남은 경험치: {}",
				progress.getUser().getId(),
				progress.getAttribute().getName(),
				currentLevel,
				currentExp);
		}

		progress.setLevel(currentLevel);
		progress.setExp(currentExp);

		log.debug("processLevelUp 종료 - 사용자: {}, 속성: {}, 최종 레벨: {}, 최종 경험치: {}",
			progress.getUser().getId(),
			progress.getAttribute().getName(),
			progress.getLevel(),
			progress.getExp());
	}

	private Map<AttributeLevelId, AttributeLevel> preloadAttributeLevels() {
		List<AttributeLevel> levels = attributeLevelRepository.findAll();

		return levels.stream()
			.collect(Collectors.toMap(
				AttributeLevel::getId,
				level -> level
			));
	}

	private Optional<Integer> getRequiredExp(AttributeLevelId key, Map<AttributeLevelId, AttributeLevel> levelCache) {
		AttributeLevel attributeLevel = levelCache.get(key);

		return Optional.ofNullable(attributeLevel).map(AttributeLevel::getRequiredExp);
	}
}