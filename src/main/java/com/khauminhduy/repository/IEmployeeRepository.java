package com.khauminhduy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khauminhduy.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmail(String email);
	
}
