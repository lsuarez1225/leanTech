package com.leantech.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leantech.prueba.model.Position;
import com.leantech.prueba.repository.PositionRepository;

@Service
public class PositionService {
	
	@Autowired
	private PositionRepository repository;

	public List<Position> getEmployeesByPosition () {
		return repository.getEmployeesByPosition();
	}	
}
