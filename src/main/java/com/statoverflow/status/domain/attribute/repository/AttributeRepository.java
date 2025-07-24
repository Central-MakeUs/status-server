package com.statoverflow.status.domain.attribute.repository;

import com.statoverflow.status.domain.master.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

}
