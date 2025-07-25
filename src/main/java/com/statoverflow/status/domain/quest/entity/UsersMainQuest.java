package com.statoverflow.status.domain.quest.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.quest.enums.QuestStatus;
import com.statoverflow.status.domain.users.entity.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
public class UsersMainQuest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id", nullable = false)
	private Users user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "main_quest_id", nullable = false)
	private MainQuest mainQuest;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private Integer attributes;

	@Column(nullable = false)
	private LocalDateTime startDate;

	@Column(nullable = false)
	private LocalDateTime endDate;

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

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private QuestStatus status;

	@PrePersist
	protected void onCreate() {
		this.title = mainQuest.getName();
		this.attribute1 = mainQuest.getAttribute1();
		this.attribute2 = mainQuest.getAttribute2();
		this.exp1 = mainQuest.getExp1();
		this.exp2 = mainQuest.getExp2();
		this.attributes = this.attribute1.getBitMask();
		if (this.attribute2 != null) {
			this.attributes |= this.attribute2.getBitMask();
		}

		this.status = QuestStatus.ACTIVE;
	}

	@OneToMany(mappedBy = "mainQuest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<UsersSubQuest> usersSubQuests = new ArrayList<>();


}
