package com.example.demo.form;

import lombok.Data;


@Data
public class EmployeeForm {

	
	private String name;
	
	private String email;
	
	private String password;
	
	private boolean enabled;
	
	private boolean force_reset_password;
	
	private Long role_id;
	

}
