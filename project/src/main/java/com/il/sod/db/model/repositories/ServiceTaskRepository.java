package com.il.sod.db.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.il.sod.db.model.entities.ServiceTask;

public interface ServiceTaskRepository extends JpaRepository<ServiceTask, Integer> {}