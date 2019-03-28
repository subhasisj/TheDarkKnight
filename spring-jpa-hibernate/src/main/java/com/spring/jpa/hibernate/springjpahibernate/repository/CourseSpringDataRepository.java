package com.spring.jpa.hibernate.springjpahibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.jpa.hibernate.springjpahibernate.entity.Course;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

	List<Course> findByName(String Name);

	List<Course> countByName(String Name);

	@Query("select c from Course c where name like '%Tutorial%'")
	List<Course> courseStartingWithTutorial();
}
