package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.BankDAO;
import com.bank.daoimpl.BankDAOImpl;
import com.bank.exception.BankException;
import com.google.gson.Gson;

/**
 * Servlet implementation class AccountByUserId
 */
public class AccountByUserId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankDAO bankDao = new BankDAOImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountByUserId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession(false);
		Gson gson=new Gson();
		String q = request.getParameter("userid");
		//session.getAttribute("userId");
		PrintWriter out=response.getWriter();
		System.out.println(q);
		
		try {
			System.out.println("in try");
			out.print(gson.toJson(bankDao.getAllAccountsByUserId(q)));
		} catch (BankException e) {
			
			out.print(e.getMessage());
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
