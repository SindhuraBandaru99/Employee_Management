package com.myproject.spring.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproject.spring.Entity.Employee;


public interface EmployeeService {
	
	List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);


}
