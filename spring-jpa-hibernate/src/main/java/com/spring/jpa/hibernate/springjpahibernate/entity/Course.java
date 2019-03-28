package com.spring.jpa.hibernate.springjpahibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@NamedQueries(value = { @NamedQuery(name = "find_all_courses", query = "select c from Course c"),
						@NamedQuery(name = "find_all_tutorial_courses", query = "select c from Course c where name like '%Tutorial%'") })
public class Course {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@UpdateTimestamp()
	private LocalDateTime lastChangedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy = "course")
//	@JsonIgnore
	private List<Review> reviews =  new ArrayList<>();
	
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<>();

	protected Course() {
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return "Course [ " + name + "Created on : " + createdDate + "Last Changed on :" + lastChangedDate + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

}
