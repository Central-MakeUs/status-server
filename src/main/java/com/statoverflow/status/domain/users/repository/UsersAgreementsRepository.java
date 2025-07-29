package com.statoverflow.status.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statoverflow.status.domain.users.entity.UsersAgreements;

public interface UsersAgreementsRepository extends JpaRepository<UsersAgreements, Long> {
}
