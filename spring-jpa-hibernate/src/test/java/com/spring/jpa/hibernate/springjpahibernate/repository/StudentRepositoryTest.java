package com.spring.jpa.hibernate.springjpahibernate.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import com.spring.jpa.hibernate.springjpahibernate.entity.Course;
import com.spring.jpa.hibernate.springjpahibernate.entity.Passport;
import com.spring.jpa.hibernate.springjpahibernate.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJpaHibernateApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {

		Student student = em.find(Student.class, 20001L);
		logger.info("Student --> {}", student);
		logger.info("Student Passport Details --> {}", student.getPassport());
	}

	@Test
	@Transactional
	public void retrievePassportAndAsociatedStudentDetails() {

		Passport passport = em.find(Passport.class, 40001L);
		logger.info("Passport --> {}", passport);
		logger.info("Student Details --> {}", passport.getStudent());

	}

	@Test
	@Transactional
	public void retrieveStudentAndCourses() {

		Student student = repository.findById(20001l);
		logger.info("Student --> {}", student);
		logger.info("Student courses--> {}", student.getCourses());

	}
	
	@Test
	@Transactional
	public void studentWithCourses() {
		
		List<Course> courses =  new ArrayList<>();
		courses.add(new Course("Dummy Course 1"));
		courses.add(new Course("Dummy Course 2"));
		
		Student findById = repository.findById(20002l);
		repository.insertStudentAndCourse(findById, courses);
	}

}
