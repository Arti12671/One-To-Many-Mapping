package com.jspiders.onetomany.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetomany.dto.CompanyDto;
import com.jspiders.onetomany.dto.EmployeeDto;

public class CompanyDaoInsert {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		
		CompanyDto company = new CompanyDto();
		company.setName("Infosys");
		company.setLocation("Banglore");
		
		EmployeeDto employee1 = new EmployeeDto();
		employee1.setName("Sakshi");
		employee1.setEmail("sakshi@gmail.com");
		employee1.setMobile(9876543211l);
		
		EmployeeDto employee2 = new EmployeeDto();
		employee2.setName("Jayu");
		employee2.setEmail("jayu@gmail.com");
		employee2.setMobile(8876543211l);
		
		EmployeeDto employee3 = new EmployeeDto();
		employee3.setName("Pallavi");
		employee3.setEmail("pallavi@gmail.com");
		employee3.setMobile(7876543211l);
		
		EmployeeDto employee4 = new EmployeeDto();
		employee4.setName("Arti");
		employee4.setEmail("arti@gmail.com");
		employee4.setMobile(9976543211l);
		
		List<EmployeeDto> employeeList = new ArrayList<EmployeeDto>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);
		employeeList.add(employee4);
		
		company.setEmployee(employeeList);
		
		openConnection();
		entityTransaction.begin();	
		
		entityManager.persist(employee1);
		entityManager.persist(employee2);
		entityManager.persist(employee3);
		entityManager.persist(employee4);
		entityManager.persist(company);
		entityTransaction.commit();
		closeConnection();
	}
	
	private static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("company");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction  = entityManager.getTransaction();
	}
	
	private static void closeConnection() {
		if (entityManagerFactory!=null) {
			entityManagerFactory.close();
		}
		if (entityManager!=null) {
			entityManager.close();
		}
		if (entityTransaction!=null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}
}


