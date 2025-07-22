package com.statoverflow.status.domain.master.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class MainSubQuestId implements Serializable {

	@Column(name = "main_quest_id", nullable = false)
	private Long mainQuestId;

	@Column(name = "sub_quest_id", nullable = false)
	private Long subQuestId;
}