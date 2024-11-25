package com.test.conttoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Position;
import com.test.service.PositionService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/test/pos")
public class PositionController {

	@Autowired
	private PositionService positionService;
	
	@GetMapping
	public Iterable<Position> findAll() {
		return positionService.findAll();
	}	
	
	@GetMapping("/{organizationCode}")
	public Iterable<Position> findByOrganizationCode(@PathVariable("organizationCode") String organizationCode) {
		return positionService.findByOrganizationCodeContains(organizationCode);
	}
	
	@GetMapping("/findById/{code}")
	public Position findById(@PathVariable("code") String code) {
		return positionService.findById(code);
	}
}
