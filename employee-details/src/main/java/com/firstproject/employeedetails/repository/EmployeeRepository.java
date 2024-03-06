package com.firstproject.employeedetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.employeedetails.entity.Employees;


@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long>{
	
	
}
