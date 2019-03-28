package com.spring.jpa.hibernate.springjpahibernate.repository;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.jpa.hibernate.springjpahibernate.SpringJpaHibernateApplication;
import com.spring.jpa.hibernate.springjpahibernate.entity.Course;
import com.spring.jpa.hibernate.springjpahibernate.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJpaHibernateApplication.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseSpringDataRepository repository;

	@Test
	public void testFindById() {

		Optional<Course> courseOptional = repository.findById(10001l);
		boolean present = courseOptional.isPresent();

		assertTrue(present);
		logger.info("{}", present);
	}

	@Test
	@DirtiesContext
	public void testingOtherSpringDataOperations() {

		Course course = new Course("Spring Security");
		repository.save(course);

		course.setName("Spring Security OAuth");
		repository.save(course);

		logger.info("There are {} Courses -> {}", repository.count(), repository.findAll());

		Sort sortOrder = new Sort(Direction.DESC, "name");
		logger.info("There are {} Courses, Sorted Format -> {}", repository.count(), repository.findAll(sortOrder));

	}

	@Test
	public void pagination() {

		PageRequest pageRequest = PageRequest.of(0, 3);

		// Print First page
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page of Courses -> {}",firstPage.getContent());

		// Print subsequent Pages
		Pageable secondPageable = firstPage.nextPageable();
		logger.info("Page 2 of Courses -> {}", repository.findAll(secondPageable).getContent());

		Pageable thirdPageable = secondPageable.next();
		logger.info("Page 3 of Courses -> {}", repository.findAll(thirdPageable).getContent());

	}
	
	@Test
	public void testCustomQueries() {
		
		logger.info("findByName() -> {}" , repository.findByName("Kubernetes"));
		logger.info("courseStartingWithTutorial() -> {}" , repository.courseStartingWithTutorial());
		
	}

}
