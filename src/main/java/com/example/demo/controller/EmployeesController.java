package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.EmployeeForm;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRole;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RoleRepository;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Employee findById(@PathVariable("id") Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Id de funcionario não encontrado"));
	}

	@PostMapping
	public Employee create(@RequestBody EmployeeForm employeeForm) {
		
		
		Employee employee = new Employee();
		employee.setName(employeeForm.getName());
		employee.setEmail(employeeForm.getEmail());
		employee.setPassword(new BCryptPasswordEncoder().encode(employeeForm.getPassword()));
		employee.setEnabled(employeeForm.isEnabled());
		employee.setForceResetPassword(employeeForm.isForce_reset_password());
		employee.setDeleted(false);

		EmployeeRole role = roleRepository.findById(employeeForm.getRole_id())
				.orElseThrow(() -> new NoSuchElementException("Id da regra não encontrado"));
		employee.setRole(role);

		return employeeRepository.save(employee);

	}

	@PutMapping("/{id}")
	public Employee update(@PathVariable("id") Long id, @RequestBody EmployeeForm employeeForm) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Id de funcionario não encontrado"));
		EmployeeRole role = roleRepository.findById(employeeForm.getRole_id())
				.orElseThrow(() -> new NoSuchElementException("Id da regra não encontrado"));
		employee.setName(employeeForm.getName());
		employee.setEmail(employeeForm.getEmail());
		employee.setPassword(new BCryptPasswordEncoder().encode(employeeForm.getPassword()));
		employee.setEnabled(employeeForm.isEnabled());
		employee.setForceResetPassword(employeeForm.isForce_reset_password());

		employee.setRole(role);

		return employeeRepository.save(employee);

	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Id não encontrado"));

		employeeRepository.delete(employee);

		return "Funcionário " + employee.getName() + " apagado com sucesso!";
	}

}
