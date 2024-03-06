package com.firstproject.employeedetails.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.employeedetails.entity.Employees;
import com.firstproject.employeedetails.exception.EmployeeNotFoundException;
import com.firstproject.employeedetails.repository.EmployeeRepository;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employees> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id) {
		Employees employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee does not exists with id "+id));
		
		return ResponseEntity.ok(employee);
		
	}
	

	@PostMapping("/employees")
	public Employees createEmployee(@RequestBody Employees employee) {
		return employeeRepository.save(employee);
		
	}
	
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employees> updateEmployee(@PathVariable Long id, @RequestBody Employees newEmployee){
		Employees employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee does not exists with id "+id));
		employee.setFirstName(newEmployee.getFirstName());
		employee.setLastName(newEmployee.getLastName());
		employee.setEmailId(newEmployee.getEmailId());
		
		
		Employees updateEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
		
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id){
		Employees employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee does not exists with id "+id));
		
		employeeRepository.delete(employee);
		
	
		}
	

}
