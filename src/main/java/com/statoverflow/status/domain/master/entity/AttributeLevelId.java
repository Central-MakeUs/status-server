package com.statoverflow.status.domain.master.entity;

import java.io.Serializable;

import com.statoverflow.status.domain.master.enums.AttributeType;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class AttributeLevelId implements Serializable {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AttributeType type;

	@Column(nullable = false)
	private Integer level;
}