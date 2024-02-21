package com.myproject.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.spring.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	

}
