package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.Transaction;

/**
 * Servlet implementation class TransactionSuccess
 */
public class TransactionSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionSuccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if(session == null)
		{
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/bank_thor_v2'>Click here to Login </a> ");
		}else {
			//Transaction t=null;
			out.print("<center><h2>Your Account Number is:"+session.getAttribute("accountNumbert")+"</h2></center>");
			out.print("<center><h2>Your Updated Balance is:"+session.getAttribute("closingBalance")+"</h2></center>");
			//out.print("<center><h2>Your Updated Balance is:"+t.getClosingBalance()+"</h2></center>");
			out.print("<p><a href='/bank_thor_v2/index.html'>Click Here to go to MainMenu</a></p>");
		}
		
	}

}
