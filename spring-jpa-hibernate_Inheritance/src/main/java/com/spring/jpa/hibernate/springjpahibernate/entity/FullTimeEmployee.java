package com.spring.jpa.hibernate.springjpahibernate.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

	private BigDecimal salary;

	
	protected FullTimeEmployee() {
		super();
	}

	public FullTimeEmployee(String name , BigDecimal salary) {
		super(name);
		this.salary = salary;
	}
	
	 
}
