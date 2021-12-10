package com.khauminhduy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.khauminhduy.entity.Employee;
import com.khauminhduy.repository.IEmployeeRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	void saveEmployeeTest() {
		Employee employee = Employee.builder()
				.firstName("Khau")
				.lastName("Duy")
				.email("example@gmail.com")
				.build();
		
		employeeRepository.save(employee);
		
		assertThat(employee.getId()).isPositive();
		
	}
	
	@Test
	@Order(2)
	void getEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		
		assertThat(employee.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	void getListEmployeeTest() {
		List<Employee> employees = employeeRepository.findAll();
		
		assertThat(employees.size()).isPositive();
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	void updateEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		
		employee.setEmail("ChangeEmail@gmail.com");
		
		employeeRepository.save(employee);
		
		assertThat(employee.getEmail()).isEqualTo("ChangeEmail@gmail.com");
		
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	void deleteEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		
		employeeRepository.delete(employee);
		
		Employee temp = null;
		
		Optional<Employee> findByEmail = employeeRepository.findByEmail("ChangeEmail@gmail.com");
		if(findByEmail.isPresent()) {
			temp = findByEmail.get();
		}
		
		assertThat(temp).isNull();
		
	}
	
}
