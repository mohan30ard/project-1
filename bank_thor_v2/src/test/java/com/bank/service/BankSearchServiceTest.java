package com.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.serviceimpl.BankSearchServiceImpl;

class BankSearchServiceTest {

	private static BankSearchService service;
	
	@BeforeAll
	public static void setUp() {
		service=new BankSearchServiceImpl();
	}
	
	@Test
	void testGetPasswordByUserId() {
		Customer c=new Customer("apple","aa");
		try {
			assertEquals(c,service.getPasswordByUserId("apple"));
		}catch(BankException e) {
			fail("Test Case failed");
		}
	}
	
	@Test
	void testGetPasswordByUserIdFalse() {
		try {
			service.getPasswordByUserId("5500");
		} catch (BankException e) {
			assertEquals("Invalid UserId or Password ", e.getMessage());
		}
	}

	@Test
	void testGetBalanceByAccountNumber() {
		Account a=new Account(null,37024270002l,null,0,null);
		long accountNumber=37024270002l;
		try {
			assertEquals(a,service.getBalanceByAccountNumber(accountNumber));
		}catch(BankException e) {
			fail("Test Case failed");
		}

	}
	
	@Test
	void testGetBalanceByAccountNumberFalse() {
		long accountNumber=89348579543l;
		try {
			service.getBalanceByAccountNumber(accountNumber);
		}catch(BankException e) {
			assertEquals("Invalid Account Number ",e.getMessage());
		}

	}

	@Test
	void testGetPasswordByempId() {
		Employee emp=new Employee("mohan_2575","Mohan");
		emp.setEmployeeId("mohan_2575");
		try {
			assertEquals(emp,service.getPasswordByempId("mohan_2575"));
		}catch(BankException e) {
			fail("Test Case failed");
		}
		
	}
	
	@Test
	void testGetPasswordByempIdFalse() {
		try {
			service.getPasswordByempId("5500");
		} catch (BankException e) {
			assertEquals("Invalid UserId or Password ", e.getMessage());
		}
		
	}

	@Test
	void testGetAllTransactionsFalse() {
		
	}

}
