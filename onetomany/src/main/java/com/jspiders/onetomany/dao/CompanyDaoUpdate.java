package com.jspiders.onetomany.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetomany.dto.CompanyDto;
import com.jspiders.onetomany.dto.EmployeeDto;

public class CompanyDaoUpdate {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		
		openConnection();
		entityTransaction.begin();
		
//		company.setLocation("Pune");		
		
		CompanyDto company = entityManager.find(CompanyDto.class, 6);
		List<EmployeeDto> employeeList = company.getEmployee();
		EmployeeDto employeeToBeUpdated  = null;
		for (EmployeeDto employee : employeeList) {
			if (employee.getId() == 15) {
				employeeToBeUpdated = employee;
				break;
			}
		}
		
		employeeToBeUpdated.setMobile(8800000000l);
//		company.setEmployee(employeeList);
		
		entityManager.persist(company);
		System.out.println(company);
		
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
