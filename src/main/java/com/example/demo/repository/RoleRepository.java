package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.EmployeeRole;

public interface RoleRepository extends JpaRepository<EmployeeRole, Long> {

}
