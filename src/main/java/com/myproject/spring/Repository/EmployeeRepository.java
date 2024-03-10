package com.myproject.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.spring.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	

}
