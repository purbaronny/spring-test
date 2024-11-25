package com.test.conttoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Organization;
import com.test.service.OrganizationService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/test/org")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
		
	@GetMapping
	public Iterable<Organization> findAll() {
		return organizationService.findAll();
	}
	
	@GetMapping("/{code}")
	public Organization findById(@PathVariable("code") String code) {
		return organizationService.findById(code);
	}
}
