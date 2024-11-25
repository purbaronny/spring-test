package com.test.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.test.entity.Organization;

public interface OrganizationRepo extends CrudRepository<Organization, String> {

}
