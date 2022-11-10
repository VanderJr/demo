package com.example.demo.commons.auditing;

import java.time.LocalDateTime;

public interface EmployeeAuditableDelete extends DeletableEntity {

	Boolean isDeleted();

	void setDeleted(Boolean deleted);
		
	LocalDateTime getDeletedAt();
	
	void setDeletedAt(LocalDateTime deletedAt);
	
	Long getDeletedByEmployeeId();
	
	void setDeletedByEmployeeId(Long principalId);

}