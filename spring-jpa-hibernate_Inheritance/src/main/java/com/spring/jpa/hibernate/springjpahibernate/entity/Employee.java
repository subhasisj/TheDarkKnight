package com.spring.jpa.hibernate.springjpahibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;


@MappedSuperclass
@NamedQuery(name = "find_all_parttimeemployees", query = "select e from PartTimeEmployee e")
@NamedQuery(name = "find_all_fulltimeemployees", query = "select e from FullTimeEmployee e")
public abstract class Employee {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String name;

	protected Employee() {
	}

	public Employee(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}
