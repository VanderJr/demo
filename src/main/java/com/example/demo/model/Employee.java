package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.demo.commons.auditing.EmployeeAuditableDelete;
import com.example.demo.commons.auditing.EmployeeAuditableEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name= "employee")
public class Employee extends EmployeeAuditableEntity implements EmployeeAuditableDelete{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	@JsonIgnore
	private String password;
	
	@NotNull
	private Boolean enabled;
	
	@NotNull
	private Boolean forceResetPassword;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleId", insertable = true, updatable = true)
	private EmployeeRole role;

	private Boolean deleted;
	
	private LocalDateTime deletedAt;
	
	private Long deletedByEmployeeId;

	@Override
	public Boolean isDeleted() {
		return deleted;
	}

	
	
	
}
