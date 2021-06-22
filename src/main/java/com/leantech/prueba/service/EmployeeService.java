package com.leantech.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leantech.prueba.model.Employee;
import com.leantech.prueba.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> getEmployees (String position, String name) {
		
		return repository.getEmployeesByPosName(position, name);
	}
	
	public Integer saveEmployee (Employee employee) {

		return repository.createEmployee(employee);		
	}
	
	public Integer updateEmployee (Employee employee) {

		return repository.updateEmployee(employee);
	}
	
	public Integer deleteEmployee (Integer id) {
		
		return repository.deleteEmployee(id);
	}
}
