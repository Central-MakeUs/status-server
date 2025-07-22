package com.statoverflow.status.domain.master.entity;

import com.statoverflow.status.domain.master.enums.ActionUnitType;
import com.statoverflow.status.domain.master.enums.ConfirmType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sub_quest")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SubQuest {

	@Id
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ActionUnitType actionUnitType;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ConfirmType confirmType;

}