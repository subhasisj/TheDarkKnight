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

import junit.framework.AssertionFailedError;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJpaHibernateApplication.class )
public class EmployeeRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void insertEmployeeAndVerify() {

		employeeRepository.insert(new FullTimeEmployee("Subhasis", new BigDecimal("140000")));
		employeeRepository.insert(new PartTimeEmployee("Avilipsha", new BigDecimal("70")));
		
		List<PartTimeEmployee> allPartTimeEmployees = employeeRepository.getAllPartTimeEmployees();
		List<FullTimeEmployee> allFullTimeEmployees = employeeRepository.getAllFullTimeEmployees();
		
		
		assertTrue(findFullTimeEmployee(allFullTimeEmployees, "Subhasis") );
		assertTrue(findPartTimeEmployee(allPartTimeEmployees, "Avilipsha") );
		
		
		logger.info("Part Time Employees Inserted are ---> {}",allPartTimeEmployees);
		logger.info("Full Time Employees Inserted are ---> {}",allFullTimeEmployees);
	}

	private boolean findFullTimeEmployee(List<FullTimeEmployee> allFullTimeEmployees , String nameTofind) {
		
		for (Employee employee : allFullTimeEmployees) {
			
			if (employee.getName().equals(nameTofind)) {
				
				return true;
			}
		}
		
		return false;
	}

	private boolean findPartTimeEmployee(List<PartTimeEmployee> allPartTimeEmployees , String nameTofind) {
		
		for (Employee employee : allPartTimeEmployees) {
			
			if (employee.getName().equals(nameTofind)) {
				
				return true;
			}
		}
		
		return false;
	}
	
	@Test
	public void contextLoads() {
		
		logger.info("Context is loaded");
		
	}
}
