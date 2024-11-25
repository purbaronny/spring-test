package com.test.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.test.entity.Users;

public interface UsersRepo extends CrudRepository<Users, Integer> {

	public Iterable<Users> findByEmailContains(String email);
}
