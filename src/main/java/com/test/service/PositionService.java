package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.entity.Position;
import com.test.entity.repo.PositionRepo;

@Service
@Transactional
public class PositionService {

	@Autowired
	private PositionRepo positionRepo;
	
	public Iterable<Position> findAll() {
		return positionRepo.findAll();
	}
	
	public Iterable<Position> findByOrganizationCodeContains(String organizationCode) {
		return positionRepo.findByOrganizationCodeContains(organizationCode);
	}
	
	public Position findById(String code) {
		return positionRepo.findById(code).get();
	}
}
