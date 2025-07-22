package com.statoverflow.status.domain.quest.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.master.entity.SubQuest;
import com.statoverflow.status.domain.master.enums.ActionUnitType;
import com.statoverflow.status.domain.quest.enums.FrequencyType;
import com.statoverflow.status.domain.quest.enums.QuestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_main_quest")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UsersSubQuest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "main_quest_id", nullable = false)
	private MainQuest mainQuest;

	@OneToOne
	@JoinColumn(name = "sub_quest_id", nullable = false)
	private SubQuest subQuest;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private FrequencyType frequencyType;

	@Column(nullable = false)
	private QuestStatus status;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ActionUnitType actionUnitType;

	@Column(nullable = false)
	private Integer actionUnitNum;

	@ManyToOne
	@JoinColumn(name = "attribute1", nullable = false)
	private Attribute attribute1;

	@ManyToOne
	@JoinColumn(name = "attribute2", nullable = true)
	private Attribute attribute2;

	@Column(nullable = false)
	private Integer exp1;

	@Column(nullable = true)
	private Integer exp2;

}
