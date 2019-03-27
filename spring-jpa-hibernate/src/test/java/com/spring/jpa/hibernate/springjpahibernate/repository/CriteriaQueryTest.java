package com.spring.jpa.hibernate.springjpahibernate.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJpaHibernateApplication.class)
public class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

//	@Test
//	public void jpql_basic() {
//		Query query = em.createNamedQuery("query_get_all_courses");
//		List resultList = query.getResultList();
//		logger.info("Select  c  From Course c -> {}", resultList);
//	}
//
//	@Test
//	public void jpql_typed() {
//		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
//
//		List<Course> resultList = query.getResultList();
//
//		logger.info("Select  c  From Course c -> {}", resultList);
//	}
//
//	@Test
//	public void jpql_where() {
//		TypedQuery<Course> query = em.createNamedQuery("query_get_100_Step_courses", Course.class);
//
//		List<Course> resultList = query.getResultList();
//
//		logger.info("Select  c  From Course c where name like '%100 Steps'-> {}", resultList);
//		// [Course[Web Services in 100 Steps], Course[Spring Boot in 100 Steps]]
//	}

	@Test
	public void findAllCourses() {

//		TypedQuery<Course> query = em.createQuery("select c from Course c ", Course.class);
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		Root<Course> from = criteriaQuery.from(Course.class);
		
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(from));
		
		List<Course> resultList = query.getResultList();
		
		assertNotNull(resultList);

		logger.info("Criteria Query Select * From Course -> {}", resultList);

	}

	@Test
	public void findAllCoursesContainingTutorial() {
		
//		TypedQuery<Course> query = em.createQuery("select c from Course c where name like 'Tutorial' ", Course.class);
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		Root<Course> from = criteriaQuery.from(Course.class);

		Predicate like_Tutorial = criteriaBuilder.like(from.get("name"), "%Tutorial%");
		
		criteriaQuery.where(like_Tutorial);
		
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(from));
		
		List<Course> resultList = query.getResultList();
		
		assertNotNull(resultList);
		
		logger.info("Criteria Query Select * From Course where name like 'Tutorial' -> {}", resultList);
		
	}

	@Test
	public void findAllCoursesWithNoStudents() {
		
//		TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty  ", Course.class);
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		Root<Course> from = criteriaQuery.from(Course.class);
		
		Predicate studentsIsEmpty = criteriaBuilder.isEmpty(from.get("students"));
		
		criteriaQuery.where(studentsIsEmpty);
		
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(from));
		
		List<Course> resultList = query.getResultList();
		
		assertNotNull(resultList);
		
		logger.info("Criteria Query 'select c from Course c where c.students is empty' -> {}", resultList);
		
	}

	@Test
	public void findAllCoursesJoinWithStudents() {
		
//		TypedQuery<Course> query = em.createQuery("select c from Course c JOIN c.students s	", Course.class);
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		Root<Course> from = criteriaQuery.from(Course.class);
		
		Join<Object, Object> join = from.join("students");
		
		
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(from));
		
		List<Course> resultList = query.getResultList();
		
		assertNotNull(resultList);
		
		logger.info("Criteria Query 'select c from Course c JOIN c.students s' -> {}", resultList);
		
	}

	@Test
	public void findAllCoursesLeftJoinWithStudents() {
		
//		TypedQuery<Course> query = em.createQuery("select c from Course c LEFT JOIN c.students s	", Course.class);
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		Root<Course> from = criteriaQuery.from(Course.class);
		
		Join<Object, Object> join = from.join("students",JoinType.LEFT);
		
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(from));
		
		List<Course> resultList = query.getResultList();
		
		assertNotNull(resultList);
		
		logger.info("Criteria Query Result Size of 'select c from Course c LEFT JOIN c.students s' -> {}", resultList.size());
		logger.info("Criteria Query 'select c from Course c LEFT JOIN c.students s' -> {}", resultList);
		
	}

	
}
