package com.spring.jpa.hibernate.springjpahibernate.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import com.spring.jpa.hibernate.springjpahibernate.entity.Course;
import com.spring.jpa.hibernate.springjpahibernate.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJpaHibernateApplication.class )
public class CourseRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseRepository repository;

	@Test
	public void testFindById() {
		
		Course course = repository.findById(10001l);
		assertEquals("JPA Hibernate Tutorial", course.getName());
	}

	@Test
	@DirtiesContext
	public void testDeletebyId() {
		
		repository.deleteById(10003l);
		assertNull(	repository.findById(10003l) );
		
	}

	@Test
	@DirtiesContext
	public void testSaveCourseUpdate() {
		
		Course course = repository.findById(10001l);
		assertEquals("JPA Hibernate Tutorial", course.getName());
		
		String newCourseName = "JPA/Hibernate with Spring Boot Tutorial";
		
		course.setName(newCourseName);
		repository.save(course);
		
		Course course_1 = repository.findById(10001l);
		assertEquals(newCourseName,course_1.getName() );
		
	}

	@Test
	@DirtiesContext
	public void testPlaywithEm() {
		
		Course course = repository.findById(repository.playingAroundWithEntityManager());
		assertEquals("Learning Containerization", course.getName());
		
	}

	@Test
	@DirtiesContext
	public void testPlaywithEm_refresh() {
		
		Course course = repository.findById(repository.playingAroundWithEntityManager());
		assertEquals("Learning Containerization", course.getName());
		
	}
	
	@Test
	@DirtiesContext
	@Transactional
	public void courseInsertReview() {
		
		repository.addReviewforCourse(10001l);
		
		Course course = repository.findById(10001l);
		List<Review> allReviews = course.getReviews();
		
//		assertEquals(2, allReviews.size());
		
	}


//	@Test
//	@DirtiesContext
//	public void testSaveCourseInsert() {
//		
//		Course course = new Course("ABAP Platform");
//		
//		
//		assertEquals(newCourseName,course_1.getName() );
//		
//	}
	
	@Test
	public void contextLoads() {
		
		logger.info("Context is loaded");
		
	}
}
