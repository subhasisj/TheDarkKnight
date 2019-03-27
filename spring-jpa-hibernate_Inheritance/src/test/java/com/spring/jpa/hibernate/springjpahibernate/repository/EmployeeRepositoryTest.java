package com.spring.jpa.hibernate.springjpahibernate.repository;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import com.spring.jpa.hibernate.springjpahibernate.entity.Employee;
import com.spring.jpa.hibernate.springjpahibernate.entity.FullTimeEmployee;
import com.spring.jpa.hibernate.springjpahibernate.entity.PartTimeEmployee;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJpaHibernateApplication.class )
public class EmployeeRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void insertEmployeeAndVerify() {

		employeeRepository.insert(new FullTimeEmployee("Monty", new BigDecimal("140000")));
		employeeRepository.insert(new PartTimeEmployee("Sweetu", new BigDecimal("70")));
		
		List<Employee> allEmployees = employeeRepository.getAllEmployees();
		
		assertEquals(2 , allEmployees.size());
		
		logger.info("Employees Inserted are ---> {}",allEmployees);
	}
	
	@Test
	public void contextLoads() {
		
		logger.info("Context is loaded");
		
	}
}
