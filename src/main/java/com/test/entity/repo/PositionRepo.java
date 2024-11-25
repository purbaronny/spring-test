package com.test.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.test.entity.Position;

public interface PositionRepo extends CrudRepository<Position, String> {

	public Iterable<Position> findByOrganizationCodeContains(String organizationCode);
}
