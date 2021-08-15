package com.bank.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.BankDAO;
import com.bank.daoimpl.BankDAOImpl;
import com.bank.exception.BankException;
import com.bank.main.Bank_Thor_Main;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;
import com.bank.service.BankCrudService;

public class BankCrudServiceImpl implements BankCrudService {
	private BankDAO bankDAO = new BankDAOImpl();

	@Override
	public Customer registerAccount(Customer customer) throws BankException {

		if (customer != null) {
			customer = bankDAO.registerAccount(customer);
		} else {
			System.out.println(customer);
			throw new BankException("not registered");
		}
		return customer;
	}

	@Override
	public Account createAccount(Account account) throws BankException {
		//account = bankDAO.createAccount(account);
		if (account != null) {
			account = bankDAO.createAccount(account);
		} else {
			throw new BankException("Account Not Opened");
		}
		return account;
	}

	@Override
	public List<Customer> getAllCustomers() throws BankException {
		List<Customer> listCustomer = new ArrayList<>();
		try {
			listCustomer = bankDAO.getAllCustomers();
		} catch (BankException b) {
			throw new BankException("No Data EXISTS as of Now");
		}

		return listCustomer;
	}

	@Override
	public List<Account> getAllAccounts() throws BankException {
		List<Account> listAccount = new ArrayList<>();
		try {
			listAccount = bankDAO.getAllAccounts();
		} catch (BankException b) {
			throw new BankException("No Data EXISTS as of Now");
		}
		return listAccount;
	}

	@Override
	public Transaction depositAmount(Transaction transaction) throws BankException {
		try {
			transaction = bankDAO.depositAmount(transaction);
		} catch (BankException b) {
			throw new BankException("NO TRANSACTION PERFORMED");
		}

		return transaction;
	}

	@Override
	public Transaction withdrawAmount(Transaction transaction) throws BankException {
		try {
			transaction = bankDAO.withdrawAmount(transaction);
		} catch (BankException b) {
			throw new BankException("NO TRANSACTION PERFORMED");
		}
		return transaction;
	}

	@Override
	public Transaction transferAmount(Transaction transaction) throws BankException {

		return transaction;
	}

	// && customer.getcustomername().matches("[a-zA-Z]{5,20}") &&
	// customer.getPassword().matches("[a-z]{3,7}@[0-9]{3}") &&
	// customer.getUserId()!=null && customer.getPassword()!=null
	@Override
	public boolean isValidLoginCredentials(Customer customer) throws BankException {
		boolean b = false;
		if (customer != null) {

			// code here for DAO
			b = bankDAO.isValidLoginCredentials(customer);
		} else {
			throw new BankException("Invalid customername or Password");
		}

		return b;
	}

	@Override
	public boolean isValidEmpLoginCredentials(Employee employee) throws BankException {
		boolean b = false;
		if (employee != null) {

			// code here for DAO
			b = bankDAO.isValidEmpLoginCredentials(employee);
		} else {
			throw new BankException("Invalid Employee username or Password");
		}

		return b;
	}

	@Override
	public void deleteAccount(long accountNumber) throws BankException {
		// TODO Auto-generated method stub
		
	}
}
