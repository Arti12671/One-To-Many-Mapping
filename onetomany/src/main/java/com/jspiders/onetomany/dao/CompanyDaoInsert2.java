package com.jspiders.onetomany.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetomany.dto.CompanyDto;
import com.jspiders.onetomany.dto.EmployeeDto;

public class CompanyDaoInsert2 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		
		CompanyDto company = new CompanyDto();
		company.setName("Wipro");
		company.setLocation("Mumbai");
		
		EmployeeDto employee1 = new EmployeeDto();
		employee1.setName("kamini");
		employee1.setEmail("kaminii@gmail.com");
		employee1.setMobile(7176543211l);
		
		EmployeeDto employee2 = new EmployeeDto();
		employee2.setName("neha");
		employee2.setEmail("neha@gmail.com");
		employee2.setMobile(7276543211l);
		
		EmployeeDto employee3 = new EmployeeDto();
		employee3.setName("punam");
		employee3.setEmail("punam@gmail.com");
		employee3.setMobile(7376543211l);
		
		EmployeeDto employee4 = new EmployeeDto();
		employee4.setName("varsha");
		employee4.setEmail("varsha@gmail.com");
		employee4.setMobile(7476543211l);
		
		List<EmployeeDto> employeeList = new ArrayList<EmployeeDto>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);
		employeeList.add(employee4);
		
		company.setEmployee(employeeList);
		
		openConnection();
		entityTransaction.begin();	
		
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
