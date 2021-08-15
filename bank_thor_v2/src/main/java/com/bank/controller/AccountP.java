package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.service.BankCrudService;
import com.bank.serviceimpl.BankCrudServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class AccountP
 */
public class AccountP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankCrudService bankCrudService = new BankCrudServiceImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out=response.getWriter();
		RequestDispatcher requestDispatcher=null;
		response.setContentType("text/html");
		if(session == null)
		{
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/bank_thor_v2'>Click here to Login </a> ");
		}else {
			//out.print("<h1>Welcome "+session.getAttribute("userId")+" ..... You have logged in successfully at "+new Date(session.getCreationTime())+"</h1>");
			Gson gson = new Gson();
			Account account = gson.fromJson(request.getReader(), Account.class);
			System.out.println(account);
			session.setAttribute("userId1", account.getUserId1());
			session.setAttribute("openingBalance", account.getOpeningBalance());
			try {
				account = bankCrudService.createAccount(account);
				session.setAttribute("accountNumber", account.getAccountNumber());
				response.sendRedirect("account.html");
				
			} catch (BankException e) {
				requestDispatcher=request.getRequestDispatcher("account.html");
				requestDispatcher.include(request, response);
				out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			}
			
			//response.sendRedirect("successa");
		//out.print("<a href='account.html'>click here to create a new account</a> ");
		//response.sendRedirect("account.html");
		
		
		out.print("<a href='logout'>Click Here to LOGOUT</a>");
	}

}}
