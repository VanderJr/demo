package com.example.demo.repository;

import com.example.demo.commons.auditing.JpaRepositoryWithSoftDelete;
import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepositoryWithSoftDelete<Employee, Long> {

}
