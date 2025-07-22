package com.statoverflow.status.domain.users.entity;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.MainQuest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_attribute_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UsersAttributeLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "users_id", nullable = false)
	private Users user;

	@ManyToOne
	@JoinColumn(name = "main_quest_id", nullable = false)
	private MainQuest mainQuest;

	@ManyToOne
	@JoinColumn(name = "attribute_id", nullable = false)
	private Attribute attribute;

	@Column(nullable = false)
	private Integer exp;

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createdAt;

}
