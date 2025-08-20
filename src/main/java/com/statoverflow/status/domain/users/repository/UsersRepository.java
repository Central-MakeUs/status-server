package com.statoverflow.status.domain.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.ProviderType;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByProviderTypeAndProviderId(ProviderType providerType, String providerId);

	boolean existsByNicknameAndTag(String nickname, String generatedTag);

	Optional<Users> findByIdAndProviderType(Long id, ProviderType providerType);
}
