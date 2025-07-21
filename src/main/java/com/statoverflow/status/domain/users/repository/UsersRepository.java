package com.statoverflow.status.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.ProviderType;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByProviderTypeAndProviderId(ProviderType providerType, String providerId);
}
