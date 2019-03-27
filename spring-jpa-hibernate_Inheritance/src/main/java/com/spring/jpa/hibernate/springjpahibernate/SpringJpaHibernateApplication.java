package com.spring.jpa.hibernate.springjpahibernate;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.jpa.hibernate.springjpahibernate.entity.Course;
import com.spring.jpa.hibernate.springjpahibernate.entity.Employee;
import com.spring.jpa.hibernate.springjpahibernate.entity.FullTimeEmployee;
import com.spring.jpa.hibernate.springjpahibernate.entity.PartTimeEmployee;
import com.spring.jpa.hibernate.springjpahibernate.entity.Student;
import com.spring.jpa.hibernate.springjpahibernate.repository.CourseRepository;
import com.spring.jpa.hibernate.springjpahibernate.repository.EmployeeRepository;
import com.spring.jpa.hibernate.springjpahibernate.repository.StudentRepository;

@SpringBootApplication
public class SpringJpaHibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository repository;
 
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaHibernateApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

//		logger.info("Course 10001 -> {}", repository.findById(10001L));
//		
//		repository.save(new Course("Cloud native Development"));
//		
//		logger.info("Timestamps Updated -> {}", repository.findById(repository.playingAroundWithEntityManager_timestamp()));
//		
//		logger.info("All Courses : {} ", repository.findAllCourses());
		
		studentRepository.saveStudentWithPassport();
		repository.addReviewforCourse(10003l);
		
		List<Course> courses =  new ArrayList<>();
		courses.add(new Course("Dummy Course 1"));
		courses.add(new Course("Dummy Course 2"));
		
		Student findById = studentRepository.findById(20002l);
		studentRepository.insertStudentAndCourse(findById, courses);
		
		employeeRepository.insert(new FullTimeEmployee("Monty", new BigDecimal("140000")));
		employeeRepository.insert(new PartTimeEmployee("Sweetu", new BigDecimal("70")));
		
		List<Employee> allEmployees = employeeRepository.getAllEmployees();
		
		
		logger.info("Employees Inserted are ---> {}",allEmployees);
		
		
		
	}

}
