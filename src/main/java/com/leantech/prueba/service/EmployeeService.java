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
		
		if (position == "" && name == "") {
			//return repository.getEmployees();
		}
		else if (position == "" && name == "") {
			//return repository.findEmployees(position, name);
		}
		else if (position == "" && name == "") {
			//return repository.findEmployees(position, name);			
		}
		else {
			//return repository.findEmployees(position, name);
			//return null;
		}
		return repository.getEmployees();
	}
	
	public Integer saveEmployee (Employee employee) {

		return repository.createEmployee(employee.getName(), employee.getPerson_id(), employee.getPosition_id());		
	}
	
	public Integer updateEmployee (Employee employee) {

		return repository.updateEmployee(employee);
	}
	
	public Integer deleteEmployee (Integer id) {
		
		return repository.deleteEmployee(id);
	}
}
