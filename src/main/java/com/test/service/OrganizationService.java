package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.entity.Organization;
import com.test.entity.repo.OrganizationRepo;

@Service
@Transactional
public class OrganizationService {

	@Autowired
	private OrganizationRepo organizationRepo;
	
	public Iterable<Organization> findAll() {
		return organizationRepo.findAll();
	}
	
	public Organization findById(String code) {
		return organizationRepo.findById(code).get();
	}
}
