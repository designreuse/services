package com.il.sod.db.dao.impl;

import com.il.sod.db.dao.IDAO;
import com.il.sod.db.model.entities.SoftDeleteEntity;
import com.il.sod.db.model.repositories.DeletableRepository;
import com.il.sod.exception.SODAPIException;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Scope("prototype")
public class GenericDaoImpl<T, ID extends Serializable> implements IDAO<T, ID>{

	protected JpaRepository<T, ID> repository;

	public GenericDaoImpl(){}

	public GenericDaoImpl(JpaRepository<T, ID> repository){
		this.repository = repository;
	}
	
	public void setRepository(JpaRepository<T, ID> repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public T create(T item) {
		return repository.save(item);
	}

	@Override
	@Transactional
	public T findById(ID id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=SODAPIException.class)
	public T delete(ID id) throws SODAPIException {
		T deletedT = repository.findOne(id);
		if (deletedT == null){
			throw new SODAPIException("item not found in the db");
		}
		repository.delete(deletedT);
		repository.flush();
		return deletedT;
	}

	@Override
	@Transactional(rollbackFor=SODAPIException.class)
	public T sofDelete(ID id) throws SODAPIException {
		T deletedT = repository.findOne(id);
		if (deletedT == null){
			throw new SODAPIException("item not found in the db");
		}
		((SoftDeleteEntity) deletedT).setDeleted(1);
		return repository.save(deletedT);
	}

	@Override
	@Transactional
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public List<T> findAllActive() {
		return ((DeletableRepository) repository).findAllByDeleted(0);
	}

	@Override
	@Transactional(rollbackFor=SODAPIException.class)
	public T update(T entity) throws SODAPIException {
		repository.save(entity);
		return entity;
	}

}