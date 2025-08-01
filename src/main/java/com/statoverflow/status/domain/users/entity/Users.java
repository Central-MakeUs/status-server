package com.statoverflow.status.domain.users.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.statoverflow.status.domain.users.enums.AccountStatus;
import com.statoverflow.status.domain.users.enums.ProviderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(nullable = false)
	private String tag;

	@Setter
	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ProviderType providerType;

	@Setter
	@Column(nullable = false)
	private String providerId;

	@Setter
	@Column(nullable = true)
	private String profileImage;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Setter
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountStatus status;

	@PrePersist
	protected void onCreate() {
		this.status = AccountStatus.ACTIVE;
	}

}

