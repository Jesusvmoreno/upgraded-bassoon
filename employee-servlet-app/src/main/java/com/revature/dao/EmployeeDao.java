package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {

	public int insert(Employee e) {
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		int pk = (int) ses.save(e);
		
		return pk;
		
	}
	
	public List<Employee> findAll() {
		//grab the session
		Session ses = HibernateUtil.getSession();
		//make an HQL - hibernate Query Language: odd mix of OOP & native
		List<Employee> emps = ses.createQuery("from Employee", Employee.class).list();
		
		return emps;
	}
	
	public boolean delete(int id) {
		
		return false;
		
	}
	public boolean udpate(Employee e) {
		
		return false;
		
	}
	
}
