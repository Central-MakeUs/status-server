package com.statoverflow.status.domain.master.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quest_theme")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestTheme {

	@Id
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer linkedAttribute;
}
