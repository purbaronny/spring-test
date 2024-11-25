package com.test.entity.repo;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.entity.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

	public Iterable<Employee> findAllByPositionCode(String positionCode);
	
	@Query("SELECT e FROM Employee e")
    Page<Employee> findAll(Pageable pageable);
	
	@Query("SELECT e FROM Employee e")
    Page<Employee> findSearchBy(Pageable pageable);
	
	// Query 1: Search by Organization Code
    @Query("SELECT e.id || '' as id, e.name AS employeeName, p.name AS positionName, o.code AS orgCode, o.name AS orgName " +
           "FROM Employee e " +
           "LEFT JOIN Position p ON p.code = e.positionCode " +
           "INNER JOIN Organization o ON o.code = p.organizationCode " +
           "WHERE o.code LIKE %:organizationCode%")
    Page<Map<String, String>> searchByOrganizationCode(@Param("organizationCode") String organizationCode, Pageable pageable);

    // Query 2: Search by Organization Name
    @Query("SELECT e.id || '' as id, e.name AS employeeName, p.name AS positionName, o.code AS orgCode, o.name AS orgName " +
           "FROM Employee e " +
           "LEFT JOIN Position p ON p.code = e.positionCode " +
           "INNER JOIN Organization o ON o.code = p.organizationCode " +
           "WHERE o.name LIKE %:organizationName%")
    Page<Map<String, String>> searchByOrganizationName(@Param("organizationName") String organizationName, Pageable pageable);
}
