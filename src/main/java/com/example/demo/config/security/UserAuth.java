package com.example.demo.config.security;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.model.EmployeeRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({ "password" })
@Getter
@Setter
@Entity
public class UserAuth extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Boolean enabled;
	private Boolean forceResetPassword;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleId", insertable = false, updatable = false)
	private EmployeeRole role;

	public UserAuth(Long id, String email, String password, String name, boolean forceResetPassword, boolean enabled,
			String roleValue) {
		super(email, password, enabled, true, true, true, List.of(new SimpleGrantedAuthority(roleValue)));
		this.id = id;
		this.email = email;
		this.name = name;
		this.forceResetPassword = forceResetPassword;
	}

}
