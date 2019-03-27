package com.spring.jpa.hibernate.springjpahibernate.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

	private BigDecimal hourlyWage;

	
	protected PartTimeEmployee() {
		super();
	}

	public PartTimeEmployee(String name , BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}
	
	 
}
