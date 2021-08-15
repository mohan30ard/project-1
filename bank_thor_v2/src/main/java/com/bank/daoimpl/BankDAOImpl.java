package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.BankDAO;
import com.bank.dbutil.PostgresConnection;
import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;

public class BankDAOImpl implements BankDAO {
	private static Logger log = Logger.getLogger(BankDAOImpl.class);

	@Override
	public Customer registerAccount(Customer customer) throws BankException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql1 = ("insert into bank_schema.logindetails(userid,password) values(?,?)");
			String sql = ("insert into bank_schema.customer(firstname,lastname,emailid,mobilenumber,userid) values(?,?,?,?,?)");
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getEmailId());
			preparedStatement.setString(4, customer.getMobileNumber());
			preparedStatement1.setString(1, customer.getUserId());
			preparedStatement.setString(5, customer.getUserId());
			preparedStatement1.setString(2, customer.getPassword());
			int c1 = preparedStatement1.executeUpdate();
			int c = preparedStatement.executeUpdate();

			if (c == 1 && c1 == 1) {
				log.info("Registration Successful");
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return customer;
	}

	@Override
	public Account createAccount(Account account) throws BankException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank_schema.accountdetails(balance,pancard,userid,name) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// preparedStatement.setDouble(1, account.getAccountNumber());
			preparedStatement.setDouble(1, account.getOpeningBalance());
			preparedStatement.setString(2, account.getPanCard());
			preparedStatement.setString(3, account.getUserId1());
			preparedStatement.setString(4, account.getName());
			int c = preparedStatement.executeUpdate();

			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					account.setAccountNumber(resultSet.getLong(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return account;
	}

	@Override
	public List<Customer> getAllCustomers() throws BankException {
		List<Customer> listCustomer=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.customer ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Customer customer=new Customer();
				customer.setUserId(resultSet.getString(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setEmailId(resultSet.getString(4));
				customer.setMobileNumber(resultSet.getString(5));
				listCustomer.add(customer);
			}
			if(listCustomer.size()==0) {
				throw new BankException("No accounts Found in DataBase");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return listCustomer;
	}

	@Override
	public List<Account> getAllAccounts() throws BankException {
		List<Account> listAccount=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.accountdetails ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Account account=new Account();
				account.setAccountNumber(resultSet.getLong(1));
				account.setPanCard(resultSet.getString(3));
				account.setOpeningBalance(resultSet.getDouble(2));
				account.setName(resultSet.getString(5));
				account.setUserId1(resultSet.getString(4));
				listAccount.add(account);
			}
			if(listAccount.size()==0) {
				throw new BankException("No accounts Found in DataBase");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return listAccount;
	}

	@Override
	public Transaction depositAmount(Transaction transaction) throws BankException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank_schema.transactions(transtype,balance,tamount,updatedbalance,accountnumber) values(?,?,?,?,?)";
			String sql1 = "update bank_schema.accountdetails set balance=? where accountnumber = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement.setString(1, transaction.getTransType());
			preparedStatement.setDouble(2, transaction.getOpeningBalance1());
			preparedStatement.setDouble(3, transaction.getAmount());
			preparedStatement.setDouble(4, transaction.getClosingBalance());
			preparedStatement.setDouble(5, transaction.getAccountNumber());
			preparedStatement1.setDouble(1, transaction.getClosingBalance());
			preparedStatement1.setDouble(2, transaction.getAccountNumber());

			int c = preparedStatement.executeUpdate();
			int c1 = preparedStatement1.executeUpdate();

			if (c == 1 && c1 == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					transaction.setTransId(resultSet.getInt(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return transaction;
	}

	@Override
	public Transaction withdrawAmount(Transaction transaction) throws BankException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank_schema.transactions(transtype,balance,tamount,updatedbalance,accountnumber) values(?,?,?,?,?)";
			String sql1 = "update bank_schema.accountdetails set balance=? where accountnumber = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement.setString(1, transaction.getTransType());
			preparedStatement.setDouble(2, transaction.getOpeningBalance1());
			preparedStatement.setDouble(3, transaction.getAmount());
			preparedStatement.setDouble(4, transaction.getClosingBalance());
			preparedStatement.setDouble(5, transaction.getAccountNumber());
			preparedStatement1.setDouble(1, transaction.getClosingBalance());
			preparedStatement1.setDouble(2, transaction.getAccountNumber());

			int c = preparedStatement.executeUpdate();
			int c1 = preparedStatement1.executeUpdate();

			if (c == 1 && c1 == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					transaction.setTransId(resultSet.getInt(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return transaction;
	}

	@Override
	public Transaction transferAmount(Transaction transaction) throws BankException {
		
		return transaction;
	}

	@Override
	public Customer getPasswordByUserId(String userId) throws BankException {
		Customer customer = new Customer();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select userid,password from bank_schema.logindetails where userid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				customer.setUserId(resultSet.getString("userId"));
				customer.setPassword(resultSet.getString("password"));
			} else {
				throw new BankException("Invalid UserId or Password ");
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return customer;
	}

	@Override
	public Account getBalanceByAccountNumber(long accountNumber) throws BankException {
		Account account = new Account();
		try (Connection connection = PostgresConnection.getConnection()) {

			String sql = "select accountnumber,balance from bank_schema.accountdetails where accountnumber =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				account.setAccountNumber(resultSet.getLong("accountnumber"));
				account.setOpeningBalance(resultSet.getDouble("balance"));
				
			} else {
				throw new BankException("Invalid Account Number ");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return account;

	}

	@Override
	public Employee getPasswordByempId(String empId) throws BankException {
		Employee employee = new Employee();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select empuserid,emppassword from bank_schema.employee where empuserid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, empId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				employee.setEmployeeId(resultSet.getString("empuserid"));
				employee.setEmployeePassword(resultSet.getString("emppassword"));
			} else {
				throw new BankException("Invalid UserId or Password ");
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return employee;
	}

	@Override
	public List<Transaction> getAllTransactions(long accountNumber) throws BankException {
		List<Transaction> statement=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.transactions where accountNumber = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Transaction transaction=new Transaction();
				transaction.setTransId(resultSet.getInt("transid"));
				transaction.setTransType(resultSet.getString("transtype"));
				transaction.setAccountNumber(resultSet.getLong("accountNumber"));
				transaction.setAmount(resultSet.getDouble("tamount"));
				transaction.setOpeningBalance1(resultSet.getDouble("balance"));
				transaction.setClosingBalance(resultSet.getDouble("updatedbalance"));
				transaction.setDate(resultSet.getString("date"));
				statement.add(transaction);
			}
			if(statement.size()==0) {
				throw new BankException("No records exists as of now with the AccountNumber  : "+accountNumber);
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		
		
		return statement;
	}

	@Override
	public boolean isValidLoginCredentials(Customer customer) throws BankException {
		boolean b=false;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select userid from bank_schema.logindetails where userid=? and password=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getUserId());
			preparedStatement.setString(2, customer.getPassword());
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				b=true;
			}else {
				throw new BankException("Invaid Login Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);//logger
			throw new BankException("Internal error occured.. Contact SYSADMIN!!!!!!!!!!!!!!!!!");
		}
		return b;
	}

	@Override
	public boolean isValidEmpLoginCredentials(Employee employee) throws BankException {
		boolean b=false;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select empuserid from bank_schema.employee where empuserid=? and emppassword=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getEmployeePassword());
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				b=true;
			}else {
				throw new BankException("Invaid Employee Login Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);//logger
			throw new BankException("Internal error occured.. Contact SYSADMIN!!!!!!!!!!!!!!!!!");
		}
		return b;
	}

	@Override
	public List<Transaction> getAllTransactions1() throws BankException {
		List<Transaction> statement=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.transactions ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Transaction transaction=new Transaction();
				transaction.setTransId(resultSet.getInt("transid"));
				transaction.setTransType(resultSet.getString("transtype"));
				transaction.setAccountNumber(resultSet.getLong("accountNumber"));
				transaction.setAmount(resultSet.getDouble("tamount"));
				transaction.setOpeningBalance1(resultSet.getDouble("balance"));
				transaction.setClosingBalance(resultSet.getDouble("updatedbalance"));
				transaction.setDate(resultSet.getString("date"));
				statement.add(transaction);
			}
			if(statement.size()==0) {
				throw new BankException("No records exists ");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
	}
		return statement;
	}

	@Override
	public List<Account> getAllAccountsByUserId(String userId) throws BankException {
		List<Account> listAccount=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.accountdetails where userid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Account account=new Account();
				account.setAccountNumber(resultSet.getLong("accountnumber"));
				account.setPanCard(resultSet.getString("pancard"));
				account.setOpeningBalance(resultSet.getDouble("balance"));
				account.setName(resultSet.getString("name"));
				account.setUserId1(resultSet.getString("userid"));
				listAccount.add(account);
			}
			if(listAccount.size()==0) {
				throw new BankException("No accounts Found in DataBase");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return listAccount;
	}

	@Override
	public void deleteAccount(long accountNumber) throws BankException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="delete from bank_schema.transactions where accountnumber=?";
			String sql1="delete from bank_schema.accountdetails where accountnumber=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
			preparedStatement.setLong(1, accountNumber);
			preparedStatement1.setLong(1, accountNumber);
			
			int c=preparedStatement.executeUpdate();
			int c1=preparedStatement1.executeUpdate();
			if(c==1 && c1==1)
			{
				System.out.println("Account deleted Successfully");
			}else {
				throw new BankException("No Account Found");
			}	
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		
	}


}
