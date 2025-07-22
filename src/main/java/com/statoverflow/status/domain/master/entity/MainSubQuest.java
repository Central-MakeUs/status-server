package com.statoverflow.status.domain.master.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main_sub_quest")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MainSubQuest {

	@EmbeddedId
	private MainSubQuestId id;


	@MapsId("mainQuestId")
	@ManyToOne
	@JoinColumn(name = "main_quest_id", insertable = false, updatable = false)
	private MainQuest mainQuest;


	@MapsId("subQuestId")
	@ManyToOne
	@JoinColumn(name = "sub_quest_id", insertable = false, updatable = false)
	private SubQuest subQuest;

	@Column(nullable = false)
	private Integer attribute1;

	@Column(nullable = false)
	private Integer exp1;

	@Column(nullable = true)
	private Integer attribute2;

	@Column(nullable = true)
	private Integer exp2;

	@Column(name = "linked_attribute", nullable = false)
	private Integer linkedAttribute;
}