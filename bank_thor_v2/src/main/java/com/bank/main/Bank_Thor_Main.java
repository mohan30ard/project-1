package com.bank.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.validations.Validations;
import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;
import com.bank.service.BankCrudService;
import com.bank.service.BankSearchService;
import com.bank.serviceimpl.BankCrudServiceImpl;
import com.bank.serviceimpl.BankSearchServiceImpl;

public class Bank_Thor_Main {

	private static Logger log = Logger.getLogger(Bank_Thor_Main.class);

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int ch = 0;
		do {
			log.info("Welcome to ThorBankingApp V1.0");
			log.info("==================================");
			log.info("\nBank Menu");
			log.info("1)Create Account");
			log.info("2)Login into your Account");
			log.info("3)EXIT");
			log.info("Enter your Choice 1-3");
			try {
				ch = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				log.info(e.getMessage());
			}
			switch (ch) {
			case 1:
				BankCrudService bankCrudService = new BankCrudServiceImpl();
				try {
					boolean valid =false;
					String firstName,lastName,emailId,mobileNumber,userId,password;
					do {
					log.info("Enter your First Name => ");
					 firstName = sc.nextLine();
					valid= Validations.IsValidFirstName(firstName);
					}while(!valid);
					do {
					log.info("Enter your Last Name => ");
					lastName = sc.nextLine();
					valid =Validations.IsValidLastName(lastName);
					}while(!valid);
					do {
					log.info("Enter your Email Id => ");
					emailId = sc.nextLine();
					valid=Validations.IsValidEmailId(emailId);
					}while(!valid);
					do {
					log.info("Enter your Password => ");
					password = sc.nextLine();
					valid=Validations.IsValidPassword(password);
					}while(!valid);
					do {
					log.info("Enter your Mobile Number => ");
					mobileNumber = sc.nextLine();
					valid=Validations.IsValidMobileNumber(mobileNumber);
					}while(!valid);
					do {
					log.info("Enter your user Id  => ");
					userId = sc.nextLine();
					valid=Validations.IsValidUserId(userId);
					}while(!valid);
					Customer customer = new Customer(firstName, lastName, emailId, mobileNumber, userId, password);
					customer = bankCrudService.registerAccount(customer);
					if (customer != null) {
						log.info("Successfully registered to Thor Bank");
						log.info("\n");
						log.info(customer);
						log.info("\n");
					}
				} catch (BankException e) {
					log.warn(e.getMessage());
				}
				break;
			case 2:
				int menuChoice = 0;
				do {
					log.info("Welcome Login Portal\n" + "press 1 for Customer Login\n" + "press 2 for Employee Login\n"
							+ "press 3 for MainMenu\n" + "press 4 to exit Application");
					try {
						menuChoice = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						log.warn(e.getMessage());
					}
					switch (menuChoice) {
					case 1:
						BankSearchService bankSearchService = new BankSearchServiceImpl();

						String userId = null;
						Customer customer = null;

						try {
							log.info("Enter UserId => ");
							userId = sc.nextLine();
							customer = bankSearchService.getPasswordByUserId(userId);

							if (customer.getPassword() != null) {
								log.info("Enter Password => ");
								String password = sc.nextLine();
								if (customer.getPassword().equals(password)) {
									log.info("\n");
									log.info("Hello " + userId);
									log.info("Welcome to Thor Bank");
									log.info("You have successfully logged in");
									log.info("\n");
									do {
										int ch1 = 0;
										log.info("Select the operation you want perform");
										log.info("press 1 to open New Account\n" + "press 2 to Transfer\n"
												+ "press 3 to Withdraw\n" + "press 4 to Deposit\n"
												+ "press 5 to view statements\n" + "press 6 to Logout");
										try {
											ch1 = Integer.parseInt(sc.nextLine());
										} catch (NumberFormatException e) {
											log.info(e.getMessage());
										}
										switch (ch1) {
										case 1:
											BankCrudService bankCrudService1 = new BankCrudServiceImpl();
											Account account = null;
											log.info("Enter your Name => ");
											String name = sc.nextLine();
											log.info("Enter your PanCard Details => ");
											String panCard = sc.nextLine();
											log.info("Enter your Opening Balance Amount => ");
											double openingBalance = Double.parseDouble(sc.nextLine());
											// log.info("Enter your user Id => ");sc.nextLine();
											String userId1 = userId;
											if (panCard.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
												account = new Account(userId1, name, panCard, openingBalance);
											} else {
												log.warn("Invalid PanCard Details");
											}
											try {
												account = bankCrudService1.createAccount(account);
												if (account != null) {
													log.info("Successfully registered to Thor Bank");
													log.info("\n");
													log.info(
															"Please Notedown Registered AccountNumber for Further Communication");
													log.info(account.getAccountNumber());
													log.info(account);
													log.info("\n");
												}
											} catch (BankException e) {
												log.info(e);
											}
											log.info("Thankyou for using Thor Banking Services");
											break;
										case 2:
											Transaction transaction2 = null;
											Transaction transaction3 = null;
											double closingBalance1 =0;
											BankCrudService bankCrudService5 = new BankCrudServiceImpl();
											BankSearchService bankSearchService5 = new BankSearchServiceImpl();
											Account account4 = new Account();
											log.info("Enter Your 11 Digit Account Number");
											long accountNumber3 = Long.parseLong(sc.nextLine());
											log.info("enter Amount to be transfered");
											double amount3 = Double.parseDouble(sc.nextLine());
											try {
												account4 = bankSearchService5.getBalanceByAccountNumber(accountNumber3);
												double balance1 = account4.getOpeningBalance();
												String transType1 = "Withdraw";
												if(amount3<balance1) {
												 closingBalance1 = balance1 - amount3;
												}else {
													log.warn("You dont have sufficient Amount in Your Account");
													break;
												}
												transaction2 = new Transaction(accountNumber3, amount3, balance1,
														closingBalance1, transType1);
												transaction2 = bankCrudService5.depositAmount(transaction2);
												log.info("Amount " + amount3
														+ "Successfully Withdrawn from your Account Number"
														+ accountNumber3);
												log.info(transaction2);
												
											} catch (BankException b) {
												log.warn(b.getMessage());
											}
											BankCrudService bankCrudService6 = new BankCrudServiceImpl();
											BankSearchService bankSearchService6 = new BankSearchServiceImpl();
											Account account5 = new Account();
											log.info("Enter 11 Digit Account Number to be Transferd");
											long accountNumber4 = Long.parseLong(sc.nextLine());
											//log.info("enter Amount to be deposited");Double.parseDouble(sc.nextLine());
											double amount5 = amount3;
											try {
												account5 = bankSearchService6.getBalanceByAccountNumber(accountNumber4);
												double balance = account5.getOpeningBalance();
												String transType = "Deposit";
												double closingBalance = balance + amount5;

												transaction3 = new Transaction(accountNumber4, amount5, balance,
														closingBalance, transType);
												transaction3 = bankCrudService6.depositAmount(transaction3);
												
											} catch (BankException d) {
												log.warn(d.getMessage());
											}

											log.info("Amount " + amount5
													+ "Successfully Transferred to Beneficiary Account "
													+ accountNumber4);
											//log.info(transaction3);
											log.info("Thankyou for using Thor Banking Services");
											log.info("\n");
											
											break;
										case 3:
											Transaction transaction1 = null;
											double closingBalance2=0;
											BankCrudService bankCrudService3 = new BankCrudServiceImpl();
											BankSearchService bankSearchService2 = new BankSearchServiceImpl();
											Account account2 = new Account();
											log.info("Enter Your 11 Digit Account Number");
											long accountNumber1 = Long.parseLong(sc.nextLine());
											log.info("enter Amount to be withdrwn");
											double amount1 = Double.parseDouble(sc.nextLine());
											try {
												account2 = bankSearchService2.getBalanceByAccountNumber(accountNumber1);
												double balance1 = account2.getOpeningBalance();
												String transType1 = "Withdraw";
												if(amount1<balance1) {
												 closingBalance2 = balance1 - amount1;
			
												transaction1 = new Transaction(accountNumber1, amount1, balance1,
														closingBalance2, transType1);
												transaction1 = bankCrudService3.depositAmount(transaction1);
												log.info("Amount " + amount1
														+ "Successfully Withdrawn from your Account Number"
														+ accountNumber1);
												log.info(transaction1);
												}else {
													log.warn("You dont have sufficient Amount in Your Account");
												}
											} catch (BankException b) {
												log.warn(b.getMessage());
											}

											
											log.info("Thankyou for using Thor Banking Services");
											log.info("\n");

											break;
										case 4:
											Transaction transaction = null;
											BankCrudService bankCrudService2 = new BankCrudServiceImpl();
											BankSearchService bankSearchService1 = new BankSearchServiceImpl();
											Account account1 = new Account();
											log.info("Enter Your 11 Digit Account Number");
											long accountNumber = Long.parseLong(sc.nextLine());
											log.info("enter Amount to be deposited");
											double amount = Double.parseDouble(sc.nextLine());
											try {
												account1 = bankSearchService1.getBalanceByAccountNumber(accountNumber);
												double balance = account1.getOpeningBalance();
												String transType = "Deposit";
												double closingBalance = balance + amount;

												transaction = new Transaction(accountNumber, amount, balance,
														closingBalance, transType);
												transaction = bankCrudService2.depositAmount(transaction);
											} catch (BankException d) {
												log.warn(d.getMessage());
											}

											log.info("Amount " + amount
													+ "Successfully deposited into your Account Number"
													+ accountNumber);
											log.info(transaction);
											log.info("Thankyou for using Thor Banking Services");
											log.info("\n");

											break;
										case 5:
											log.info("Enter AccountNumber to get the Statement ");
											long accountNumber5 = Long.parseLong(sc.nextLine());
											try {
												List<Transaction> statement = bankSearchService
														.getAllTransactions(accountNumber5);
												if (statement != null && statement.size() > 0) {
													log.info("Statement for the Account Number  " + accountNumber5);
													for (Transaction t : statement) {
														log.info(t);
													}
												}
											} catch (BankException e) {
												log.error(e.getMessage());
											}
											log.info("Thankyou for using Thor Banking Services");

											break;
										case 6:
											log.info("Thank you for using Thor Banking Services.  Goodbye....!");
											log.info("May Thor be With You :)");
											log.info("Successfully logged out of Thor Bank");
											System.exit(0);
											break;
										default:
											log.info("entered option is invalid");
											break;
										}

									} while (ch != 6);
									break;

								} else {
									log.warn("invalid userid or password entered");
									break;
								}
							} else {
								log.warn("invalid userid or password entered");
								break;
							}
						} catch (BankException | NullPointerException e1) {
							log.warn("User Id not Exists");
						}

						break;
					case 2:
						BankSearchService bankSearchService3 = new BankSearchServiceImpl();

						String empId = null;
						Employee employee = null;
						log.info("Enter EployeeId => ");
						empId = sc.nextLine();
						try {
							employee = bankSearchService3.getPasswordByempId(empId);
//
							if (employee.getEmployeePassword() != null ) {
								System.out.println(employee.getEmployeePassword());
								log.info("Enter Password => ");
								String password1 = sc.nextLine();
								if (employee.getEmployeePassword().equals(password1)) {
									log.info("\n");
									log.info("Hello " + empId);
									log.info("Welcome to Thor Bank");
									log.info("You have successfully logged in ");
									log.info("entered employee login portal");
								
							
							int ch2 = 0;
							do {
								log.info("1)To View All the Accounts");
								log.info("2)To Approve Account");
								log.info("3)To View Registered Customers");
								log.info("4)LogOut");
								try {
									ch2 = Integer.parseInt(sc.nextLine());
								} catch (NumberFormatException e) {
									log.info(e.getMessage());
								}
								switch (ch2) {
								case 1:
									BankCrudService bankCrudService4 = new BankCrudServiceImpl();
									try {
										List<Account> listAccount = bankCrudService4.getAllAccounts();
										if (listAccount != null && listAccount.size() > 0) {
											// System.out.println("Statement for the Account Number "+accountNumber3);
											for (Account a : listAccount) {
												log.info(a);
											}
										}
									} catch (BankException e) {
										log.error(e.getMessage());
									}
									log.info("\n");
									log.info("Thankyou for using Thor Banking Services");
									log.info("\n");
									break;
								case 2:
									break;
								case 3:
									BankCrudService bankCrudService5 = new BankCrudServiceImpl();
									try {
										List<Customer> listCustomer = bankCrudService5.getAllCustomers();
										if (listCustomer != null && listCustomer.size() > 0) {
											for (Customer c : listCustomer) {
												log.info(c);
											}
										}
									} catch (BankException e) {
										log.error(e.getMessage());
									}
									log.info("\n");
									log.info("Thankyou for using Thor Banking Services");
									log.info("\n");
									break;
								case 4:
									log.info("Thank you for using Thor Banking Services.  Goodbye....!");
									log.info("May Thor be With You :)");
									log.info("Successfully logged out of Thor Bank");
									System.exit(0);
									break;
								default:
									log.info("entered option is invalid");
									break;
								}
							} while (ch2 != 4);
								}else {
									log.warn("invalid userid or password entered");
									break;
								}
								
							
							}else {
									log.warn("invalid userid or password entered");
									break;
								}
							
						} catch (BankException e) {

							log.warn(e.getMessage());
							
						}
						break;
					case 3:
						log.info("Going to MainMenu.........");
						break;
					case 4:
						log.info("Thank you for using Thor Banking Services.  Goodbye....!");
						log.info("May Thor be With You :)");
						System.exit(0);
					default:
						log.info("entered option is invalid");
						break;
					}
				} while (menuChoice != 3);
				break;
			case 3:
				log.info("Thank you for using Thor Banking Services.  Goodbye....!");
				log.info("May Thor be With You :)");
				break;
			default:
				log.warn("Invalid Choice... Please enter input between 1-3");
				break;
			}
		} while (ch != 3);
		sc.close();
	}
}
