package com.statoverflow.status.domain.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statoverflow.status.domain.master.entity.NicknameGenerator;
import com.statoverflow.status.domain.master.enums.DefaultNicknameType;

public interface NicknameGeneratorRepository extends JpaRepository<NicknameGenerator, Long> {
	List<NicknameGenerator> findAllByType(DefaultNicknameType defaultNicknameType);
}
