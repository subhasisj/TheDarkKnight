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
import com.spring.jpa.hibernate.springjpahibernate.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;
	private TypedQuery<Course> createNamedQuery;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Course findById(Long id) {

		return em.find(Course.class, id);
	}

	public void deleteById(Long id) {

		Course course = findById(id);
		em.remove(course);
	}

	public Course save(Course course) {

		if (course.getId() == 0l) {

//			save a course
			em.persist(course);
		} else {
//			Update a course
			em.merge(course);
		}

		return course;
	}

	public long playingAroundWithEntityManager() {

		String courseName = "Learning Containerization";

		Course course = new Course("Learning Containerization");
		em.persist(course);

		em.flush();
		em.detach(course);
		course.setName(courseName + " With Dockers");

		return course.getId();

	}

	public long playingAroundWithEntityManager_refresh() {

		String courseName = "Learning Containerization";

		Course course = new Course("Learning Containerization");
		em.persist(course);

		em.flush();
		course.setName(courseName + " With Kubernetes");

		em.refresh(course);

		return course.getId();

	}

	public long playingAroundWithEntityManager_timestamp() {

		String courseName = "Learning GIT";

		Course course = new Course(courseName);
		em.persist(course);

		course.setName(courseName + " With Eclipse IDE");
		em.flush();

		return course.getId();

	}

	public List<Course> findAllCourses() {
		
		createNamedQuery = em.createNamedQuery("find_all_courses" ,Course.class);
		List<Course> resultList = createNamedQuery.getResultList();
		return resultList;

	}
	
	public void addReviewforCourse(Long courseId ) {
		
		Course course = findById(courseId);

		logger.info("course.getReviews() --> {}", course.getReviews());
		
		Review review1 = new Review("5","Testing the review");
		course.addReview( review1 );
		review1.setCourse(course);		
		Review review2 = new Review("3","Room for improvement");
		course.addReview( review2 );
		review2.setCourse(course);
		
		logger.info("course.getReviews() --> {}", course.getReviews());
		
		
	}
}
