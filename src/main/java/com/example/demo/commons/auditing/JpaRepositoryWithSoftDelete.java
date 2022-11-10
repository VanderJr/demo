package com.example.demo.commons.auditing;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.commons.security.AuthHelper;


@NoRepositoryBean
public interface JpaRepositoryWithSoftDelete<T extends EmployeeAuditableDelete, ID extends Serializable> extends JpaRepository<T, ID> {

	
	@Transactional
	@Override
	default void deleteById(ID id) {
		T entity = findById(id).orElseThrow(() -> new NoSuchElementException("Id não encontrado"));
		delete(entity);
	}
	
	@Transactional
	@Override
	default void delete(T entity) {
		if (entity.isDeleted()) {
			throw new NoSuchElementException("O objeto já foi deletado. Tente recarregar a pagina.");
		}
		entity.setDeleted(true);
		entity.setDeletedAt(LocalDateTime.now());
		entity.setDeletedByEmployeeId(AuthHelper.getPrincipalId());
		save(entity);
		
	}

}