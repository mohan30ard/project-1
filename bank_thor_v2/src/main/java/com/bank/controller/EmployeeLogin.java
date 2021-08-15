package com.bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.service.BankCrudService;
import com.bank.serviceimpl.BankCrudServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class EmployeeLogin
 */
public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BankCrudService bankCrudService = new BankCrudServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Gson gson=new Gson();
//		String q = request.getParameter("filter");
//		PrintWriter out=response.getWriter();
//		System.out.println(q);
//		if(q.equals("all")) {
//		try {
//			
//			out.print(gson.toJson(emp_Details.getAllCustomerDetails()));
//		} catch (BusinessException e) {
//			e.getMessage();
//		}
//		}
//		else if(q.equals("name")) {
//			String name=request.getParameter("criteria");
//			
//			try {
//				out.print(gson.toJson(emp_Details.getCustomerByName(name)));
//			} catch (BusinessException e) {
//				e.getMessage();
//			}
//		}
	}

}
