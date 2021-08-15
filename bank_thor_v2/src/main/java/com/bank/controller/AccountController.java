package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.service.BankCrudService;
import com.bank.service.BankSearchService;
import com.bank.serviceimpl.BankCrudServiceImpl;
import com.bank.serviceimpl.BankSearchServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class AccountController
 */
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static Logger log = Logger.getLogger(AccountController.class);

	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private BankCrudService bankCrudService = new BankCrudServiceImpl();
	private BankSearchService bankSearchService = new BankSearchServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		Gson gson=new Gson();
		//Account account = gson.fromJson(request.getReader(), Account.class);
		PrintWriter out=response.getWriter();//getStudentById(Integer.parseInt(request.getParameter("id"))
		try {
			out.print(gson.toJson(bankSearchService.getBalanceByAccountNumber(Long.parseLong(request.getParameter("accountNumber")))));
		} catch (NumberFormatException | BankException e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/bank_thor_v2'>Click here to Login </a> ");
		}else {
		Gson gson = new Gson();
		Account account = gson.fromJson(request.getReader(), Account.class);
		System.out.println(account);
		session.setAttribute("userId1", account.getUserId1());
		session.setAttribute("openingBalance", account.getOpeningBalance());
		RequestDispatcher requestDispatcher=null;
		try {
			account = bankCrudService.createAccount(account);
			session.setAttribute("accountNumber", account.getAccountNumber());
			//response.sendRedirect(index.html);
			
//			if(session.getAttribute("accountNumber") != null) {
//				requestDispatcher=request.getRequestDispatcher("successa");
//				requestDispatcher.include(request, response);
//			}
			log.info(account);
		} catch (BankException e) {
			System.out.println(e);

		}
		response.setContentType("application/json;charset=UTF-8");
		//PrintWriter out = response.getWriter();
		out.print(gson.toJson(account));
		out.print("<a href='logout'>Click Here to LOGOUT</a>");
	}}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
