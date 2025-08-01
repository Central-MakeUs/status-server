package com.statoverflow.status.domain.attribute.repository;

import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersAttributeProgressRepository extends JpaRepository<UsersAttributeProgress, Long> {

    List<UsersAttributeProgress> findByUserId(Long userId);

	UsersAttributeProgress findByUserIdAndAttributeId(Long usersId, int attributeId);
}
