package com.leantech.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.leantech.prueba.model.Employee;
import com.leantech.prueba.service.EmployeeService;
import com.leantech.prueba.util.Response;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	Response responseObject;
	
	@GetMapping(value = "/employee/list")
	private ResponseEntity<Object> getEmployees (@RequestParam(defaultValue = "") String pos, @RequestParam(defaultValue = "") String name) {
		
		List<Employee> response = service.getEmployees(pos, name);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/employee/create")
	private ResponseEntity<Object> saveEmployee (@RequestBody Employee employee) {
		
		responseObject = new Response();
		
		Integer rowsAffected = service.saveEmployee(employee);
		
		responseObject.setCode("OK");
		responseObject.setObject(rowsAffected);
		
		return ResponseEntity.ok(responseObject);
	}
	
	@PostMapping(value = "/employee/update")
	private ResponseEntity<Object> updateEmployee (@RequestBody Employee employee) {
		
		responseObject = new Response();
		
		if (employee.getId() != 0) {
			Integer rowsAffected = service.updateEmployee(employee);
			
			responseObject.setCode("OK");
			responseObject.setObject(rowsAffected);
		}
		else {
			responseObject.setCode("NOOK");
			responseObject.setObject("Enter an existing employee id");
		}
		
		return ResponseEntity.ok(responseObject);
	}
	
	@PostMapping(value = "/employee/delete")
	private ResponseEntity<Object> deleteEmployee (@RequestParam Integer id) {
		
		responseObject = new Response();
		
		Integer rowsAffected = service.deleteEmployee(id);
		
		responseObject.setCode("OK");
		responseObject.setObject(rowsAffected);
		
		return ResponseEntity.ok(responseObject);
	}
}
