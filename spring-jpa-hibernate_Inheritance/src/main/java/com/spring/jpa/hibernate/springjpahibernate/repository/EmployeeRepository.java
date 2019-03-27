package com.spring.jpa.hibernate.springjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.jpa.hibernate.springjpahibernate.entity.Course;
import com.spring.jpa.hibernate.springjpahibernate.entity.Employee;
import com.spring.jpa.hibernate.springjpahibernate.entity.Review;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	EntityManager em;
	private TypedQuery<Course> createNamedQuery;
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	public void deleteById(Long id) {

//		Course course = findById(id);
//		em.remove(course);
	}

	public void insert(Employee employee) {

			em.persist(employee);
	}

	public List<Employee> getAllEmployees() {
		
		return em.createNamedQuery("find_all_employees",Employee.class).getResultList();
	}
	
}
