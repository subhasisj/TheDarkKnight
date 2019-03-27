package com.spring.jpa.hibernate.springjpahibernate.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
public class JPQLTest {

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
	public void findCoursesWithoutStudents() {

		TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty ", Course.class);

		List<Course> resultList = query.getResultList();

		assertNotNull(resultList);

		logger.info("select c from Course c where c.students is empty --> {}", resultList);

	}

	@Test
	public void findCoursesWithAtleast2Students() {

		TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2 ", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("select c from Course c where size(c.students) >= 2 --> {}", resultList);

	}

	@Test
	public void findCoursesOrderedByStudents() {

		TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc",
				Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("elect c from Course c order by size(c.students) --> {}", resultList);

	}

	@Test
	@Transactional
	public void jpqlJoin() {

		Query query = em.createQuery("select c,s from Course c JOIN c.students s ");

		List<Object[]> resultList = query.getResultList();

		logger.info("Join Result Size  --> {}", resultList.size());

		for (Object[] result : resultList) {
			logger.info("Course : {} Student {} ", result[0], result[1]);
		}

	}
}
