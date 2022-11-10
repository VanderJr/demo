package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Table(name = "employee_role")
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
public class EmployeeRole implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String value;
	
	private String name;
	
	private String description;
	
	private Integer sortingOrder;
	
	private Boolean active;

	@Override
	public String getAuthority() {
		return name;
	}
}
