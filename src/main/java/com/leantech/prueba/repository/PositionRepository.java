package com.leantech.prueba.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.leantech.prueba.model.Employee;
import com.leantech.prueba.model.Person;
import com.leantech.prueba.model.Position;

@Repository
public class PositionRepository {

	@Autowired
	JdbcTemplate template;
	
	public List<Position> getEmployeesByPosition () {
		
		// Query for getting all positions with their employees and persons.
		String query = "SELECT a.id as position_id, a.name as position_name, b.position_id as position_id, b.id as employee_id, b.name as employee_name, b.salary as employee_salary, c.id as person_id, c.name as person_name, c.lastName as person_lastname, c.address as person_address, c.cellphone as person_cellphone, c.cityName as person_cityname FROM POSITION a LEFT JOIN EMPLOYEE b ON (b.POSITION_ID = a.ID) LEFT JOIN PERSON c ON (c.id = b.person_id) ORDER BY a.id, b.salary DESC";
		
		return template.query(query, new ResultSetExtractor<List<Position>>() {

			@Override
			public List<Position> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Position lastPosition = new Position();
				List<Position> list = new ArrayList<Position>();
				Map<Integer, Position> positionKeyPositionMap = new HashMap<Integer, Position>();
				
				while (rs.next()) {
					Integer positionKey = rs.getInt("position_id");
					
					// Get the saved position object, if exists!
					Position position = positionKeyPositionMap.get(positionKey);
					if (position == null) {
						
						// Avoids insert the first lastPosition object that is empty. 
						if (lastPosition.getId() != 0) {
							list.add(lastPosition);
						}
						
						position = new Position();
						position.setId(positionKey);
						position.setName(rs.getString("position_name"));
						
						positionKeyPositionMap.put(positionKey, position);
					}
					
					Integer employeeKey = rs.getInt("employee_id");
					Employee employee = new Employee();
					employee.setId(employeeKey);
					employee.setName(rs.getString("employee_name"));
					employee.setSalary(rs.getInt("employee_salary"));
					employee.setPosition_id(position.getId());
					employee.setPerson_id(rs.getInt("position_id"));
					
					Integer personKey = rs.getInt("person_id");
					Person person = new Person();
					person.setId(personKey);
					person.setName(rs.getString("person_name"));
					person.setLastName(rs.getString("person_lastname"));
					person.setAddress(rs.getString("person_address"));
					person.setCellphone(rs.getString("person_cellphone"));
					person.setCityName(rs.getString("person_cityname"));
					
					// Add the person object to employee.
					employee.setPerson(person);
					
					// Add the employees list to position object.
					position.getEmployees().add(employee);
					
					// Assign the current object to the temporally variable for comparing ahead.
					lastPosition = position;
				}
				
				// Add last position object to list.
				list.add(lastPosition);
				
				return list;
			}
			
		});
	}
}
