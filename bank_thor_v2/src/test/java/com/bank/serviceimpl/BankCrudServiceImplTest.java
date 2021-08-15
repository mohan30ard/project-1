package com.bank.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.service.BankCrudService;


class BankCrudServiceImplTest {
	private static BankCrudService service;
	
	@BeforeAll
	public static void setUp() {
		service=new BankCrudServiceImpl();
	}
	
	@Test
	void testRegisterAccount() {
		Customer c=new Customer("Mohan", "kumar", "123@gmail.com", "9963286630", "aapt", "1");
		try {
			assertEquals(c,service.registerAccount(c));
		}catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testCreateAccount() {
		Account a=new Account("apple", "mohan", "APPLE9999E", 3456);
		try {
			assertEquals(a,service.createAccount(a));
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDepositAmount() {
		
	}

	@Test
	void testWithdrawAmount() {
		
	}

	@Test
	void testTransferAmount() {
		
	}

}
