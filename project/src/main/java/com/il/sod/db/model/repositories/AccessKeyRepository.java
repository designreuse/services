package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.AccessKey;

public interface AccessKeyRepository extends JpaRepository<AccessKey, Integer> {}