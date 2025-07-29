package com.statoverflow.status.domain.master.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.statoverflow.status.domain.master.enums.TermsType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "terms_and_conditions", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"type", "version"}) // 약관 종류별로 버전은 고유해야 함
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class TermsAndConditions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20)
	private String version;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private TermsType type;

	@Column(nullable = false, length = 2048)
	private String link; // 노션 문서 링크

	@Column(nullable = false)
	private Boolean isEssential;

	@Column(nullable = false)
	private LocalDate effectiveDate; // 약관 적용 시작일

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt; // 생성일시

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedAt; // 수정일시

}
