package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.BankDAO;
import com.bank.daoimpl.BankDAOImpl;
import com.bank.exception.BankException;
import com.google.gson.Gson;

/**
 * Servlet implementation class DeleteAccount
 */
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
    BankDAO bankDAO = new BankDAOImpl();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson=new Gson();
		Long q = Long.parseLong(request.getParameter("accountNumber"));
		//session.getAttribute("userId");
		PrintWriter out=response.getWriter();
		System.out.println(q);
		
		try {
			System.out.println("in try");
			//out.print(gson.toJson(bankDAO.deleteAccount(q)));
			bankDAO.deleteAccount(q);
		//	out.print(gson.toJson("Account Deleted Successfully"));
		} catch (BankException e) {
			
			out.print(e.getMessage());
		
		}
	}

}
