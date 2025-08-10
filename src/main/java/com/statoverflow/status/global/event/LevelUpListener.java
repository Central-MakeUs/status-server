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

		log.debug("=== processLevelUp 시작 ===");
		log.debug("속성: {}, 현재레벨: {}, 현재경험치: {}", type, currentLevel, currentExp);

		UsersAttributeProgress progress = usersAttributeProgressRepository.save(originProgress);

		while (true) {
			// 현재 레벨의 required_exp를 조회 (다음 레벨이 아니라!)
			AttributeLevelId currentLevelId = new AttributeLevelId(type, currentLevel);
			Optional<Integer> currentRequiredExp = getRequiredExp(currentLevelId, levelCache);

			log.debug("현재 레벨 체크: {}, 필요 경험치: {}, 현재 경험치: {}",
				currentLevelId, currentRequiredExp.orElse(-1), currentExp);

			// 현재 레벨 정보가 없으면 종료 (레벨 1은 required_exp가 없을 수 있음)
			if (currentRequiredExp.isEmpty()) {
				log.debug("❌ 현재 레벨 정보가 없어서 레벨업 종료: {}", currentLevelId);
				break;
			}

			// 현재 경험치가 현재 레벨의 required_exp보다 적으면 레벨업 불가
			if (currentExp < currentRequiredExp.get()) {
				log.debug("❌ 경험치 부족으로 레벨업 종료 - 현재: {}, 필요: {}",
					currentExp, currentRequiredExp.get());
				break;
			}

			// 다음 레벨이 존재하는지 확인
			AttributeLevelId nextLevelId = new AttributeLevelId(type, currentLevel + 1);
			if (!levelCache.containsKey(nextLevelId)) {
				log.debug("❌ 다음 레벨이 존재하지 않아서 레벨업 종료: {}", nextLevelId);
				break;
			}

			// 레벨업 처리
			int oldLevel = currentLevel;
			currentLevel++;
			currentExp -= currentRequiredExp.get();

			log.debug("✅ 레벨업 성공! 레벨: {} -> {}, 사용된 경험치: {}, 남은 경험치: {}",
				oldLevel, currentLevel, currentRequiredExp.get(), currentExp);
		}

		progress.setLevel(currentLevel);
		progress.setExp(currentExp);

		log.debug("=== processLevelUp 종료 - 최종 레벨: {}, 최종 경험치: {} ===",
			progress.getLevel(), progress.getExp());
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