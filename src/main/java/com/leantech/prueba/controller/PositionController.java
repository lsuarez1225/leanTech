package com.leantech.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.leantech.prueba.model.Position;
import com.leantech.prueba.service.PositionService;
import com.leantech.prueba.util.Response;

@Controller
public class PositionController {

	@Autowired
	private PositionService service;
	
	Response responseObject;
	
	@GetMapping(value = "/position/list")
	public ResponseEntity<List<Position>> getEmployeesByPosition () {
		
		responseObject = new Response();
		
		List<Position> list = service.getEmployeesByPosition();
		
		responseObject.setCode("OK");
		responseObject.setObject(list);
		
		return ResponseEntity.ok(list);
	}
}
