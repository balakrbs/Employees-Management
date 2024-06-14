package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;    //importing the repository
	
	public Employee saveEmployee(Employee employee) {		//Adding data's to the database
		return employeeRepository.save(employee);
		
	}
	
	public List<Employee> getAllEmployee(){					// Getting the list of employees from the database
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(String id){		//Getting the employee id to search the employee details
		return employeeRepository.findById(id);
		
	}


}
