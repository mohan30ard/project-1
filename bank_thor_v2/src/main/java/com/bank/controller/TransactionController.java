package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bank.exception.BankException;
import com.bank.model.Transaction;
import com.bank.service.BankCrudService;
import com.bank.service.BankSearchService;
import com.bank.serviceimpl.BankCrudServiceImpl;
import com.bank.serviceimpl.BankSearchServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class TransactionController
 */
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TransactionController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionController() {
        super();
        // TODO Auto-generated constructor stub
    }
    private BankCrudService bankCrudService = new BankCrudServiceImpl();
	private BankSearchService bankSearchService = new BankSearchServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		Gson gson=new Gson();
		PrintWriter out=response.getWriter();
		try {
			out.print(gson.toJson(bankCrudService.getAllAccounts()));
		} catch (BankException e) {
			
			log.info(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson=new Gson(); 
		Transaction transaction=gson.fromJson(request.getReader(), Transaction.class);
		try {
			transaction = bankCrudService.depositAmount(transaction);
		} catch (BankException e) {
			log.info(e.getMessage());
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(gson.toJson(transaction));
		
	}

}
