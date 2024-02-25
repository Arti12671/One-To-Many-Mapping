package com.jspiders.onetomany.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetomany.dto.CompanyDto;
import com.jspiders.onetomany.dto.EmployeeDto;

public class CompanyDaoDelete2 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		
		openConnection();
		entityTransaction.begin();
		
		CompanyDto company = entityManager.find(CompanyDto.class, 6);
		List<EmployeeDto> employeeList = company.getEmployee();
		EmployeeDto employeeToBeDeleted = null;
		
		for (EmployeeDto employee : employeeList) {
			if (employee.getId() == 17 ) {
				employeeToBeDeleted  = employee;
				break;
			}
		}
		employeeList.remove(employeeToBeDeleted);		
		company.setEmployee(employeeList);
		entityManager.persist(company);
		
		entityManager.remove(employeeToBeDeleted);
		
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
