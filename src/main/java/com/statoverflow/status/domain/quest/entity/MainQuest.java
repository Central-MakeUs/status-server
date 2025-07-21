package com.statoverflow.status.domain.quest.entity;

import com.statoverflow.status.domain.attribute.enums.AttributeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attribute")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainQuest {

	@Id
	private Integer bitMask;

	@Column(nullable = false, unique = true)
	private Integer attributeId;

	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AttributeType type;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Integer defaultLevel;

}
