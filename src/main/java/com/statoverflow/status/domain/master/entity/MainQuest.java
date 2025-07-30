package com.statoverflow.status.domain.master.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main_quest")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class MainQuest {

	@Id
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme_id", nullable = false)
	private QuestTheme theme;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute1", nullable = false, referencedColumnName = "id")
	private Attribute attribute1;

	@Column(nullable = false)
	private Integer exp1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute2", nullable = true, referencedColumnName = "id")
	private Attribute attribute2;

	@Column(nullable = false)
	private Integer exp2;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer linkedAttribute;

	@Column(nullable = false)
	private String npcName;

}
