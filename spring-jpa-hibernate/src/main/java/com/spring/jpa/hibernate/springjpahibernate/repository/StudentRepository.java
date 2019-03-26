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
import com.spring.jpa.hibernate.springjpahibernate.entity.Passport;
import com.spring.jpa.hibernate.springjpahibernate.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;
	private TypedQuery<Student> createNamedQuery;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Student findById(Long id) {

		return em.find(Student.class, id);
	}

	public void deleteById(Long id) {

		Student student = findById(id);
		em.remove(student);
	}

	public Student save(Student student) {

		if (student.getId() == 0l) {

			em.persist(student);
		} else {
			em.merge(student);
		}

		return student;
	}

	public void saveStudentWithPassport() {

		Passport passport = new Passport("J8484718");
		em.persist(passport);
		
		Student student = new Student("Darth Vader");	
		em.persist(student);
		
		student.setPassport(passport);
		em.flush();

	}

	public long playingAroundWithEntityManager_refresh() {

		String courseName = "Learning Containerization";

		Student course = new Student("Learning Containerization");
		em.persist(course);

		em.flush();
		course.setName(courseName + " With Kubernetes");

		em.refresh(course);

		return course.getId();

	}

	public long playingAroundWithEntityManager_timestamp() {

		String courseName = "Learning GIT";

		Student course = new Student(courseName);
		em.persist(course);

		course.setName(courseName + " With Eclipse IDE");
		em.flush();

		return course.getId();

	}

	public List<Student> findAllCourses() {
		
		createNamedQuery = em.createNamedQuery("find_all_courses" ,Student.class);
		List<Student> resultList = createNamedQuery.getResultList();
		return resultList;

	}
	 
	public void insertStudentAndCourse(Student student , List<Course> courses) {
		
		for (Course course : courses) {
			
			student.addCourse(course);
			course.addStudent(student);
			
			logger.info("Setting Course {} for Student {}",course , student);
			
			em.persist(student);
			em.persist(course);
		}
		
		
	}
}
