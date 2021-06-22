package com.leantech.prueba.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.leantech.prueba.model.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private JdbcTemplate template;
	
	public List<Employee> getEmployees () {
		
		String query = "SELECT * FROM employee";
		
		List<Employee> employees = template.query(query, new BeanPropertyRowMapper<Employee>(Employee.class));
		
		return employees;
	}
	
	public Integer createEmployee (String name, Integer person, Integer position) {
		
		String query = "INSERT INTO employee (name, person_id, position_id) VALUES (?, ?, ?) ";
		
		return template.update(query, name, person, position);
	}
	
	public Integer updateEmployee (Employee employee) {
		
		String query = "UPDATE EMPLOYEE SET name = ?, person_id = ?, position_id = ? WHERE id = ?";
		
		return template.update(query, employee.getName(), employee.getPerson_id(), employee.getPosition_id(), employee.getId());
	}
	
	public Integer deleteEmployee (Integer id) {
		
		String query = "DELETE FROM EMPLOYEE WHERE id = ?";
		
		return template.update(query, id);
	}
	
}
