package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.BankCrudService;
import com.bank.serviceimpl.BankCrudServiceImpl;

/**
 * Servlet implementation class EmployeeLoginController
 */
public class EmployeeLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankCrudService bankCrudService=new BankCrudServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Employee employee = new Employee();
		employee.setEmployeeId(request.getParameter("userid"));
		employee.setEmployeePassword(request.getParameter("password"));	
		RequestDispatcher requestDispatcher=null;
		try {
			if(bankCrudService.isValidEmpLoginCredentials(employee)) {
				//success
				//HttpSession session=request.getSession();
				//session.setAttribute("EmpuserId", employee.getEmployeeId());
				response.sendRedirect("empindex.html");
				//requestDispatcher=request.getRequestDispatcher("success");
				//requestDispatcher.forward(request, response);
			}
		} catch (BankException e) {
			//failure
			PrintWriter out=response.getWriter();
			System.out.println(e);
			requestDispatcher=request.getRequestDispatcher("employee.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			
	}

}}
