package com.statoverflow.status.domain.attribute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statoverflow.status.domain.users.entity.UsersAttributeLog;

public interface UsersAttributeLogRepository extends JpaRepository<UsersAttributeLog, Long> {
}
