package com.statoverflow.status.domain.master.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main_quest")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainQuest {

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "theme_id", nullable = false)
	private QuestTheme theme;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer linkedAttribute;

	@Column(nullable = false)
	private String npcName;

}
