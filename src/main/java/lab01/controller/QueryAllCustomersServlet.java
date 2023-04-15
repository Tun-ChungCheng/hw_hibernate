package lab01.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab01.model.CustomerBean;
import lab01.service.CustomerService;
import lab01.service.impl.CustomerServiceImpl;

@WebServlet("/lab01/queryCustomer.do")
public class QueryAllCustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService ms = new CustomerServiceImpl();
		List <CustomerBean>list = ms.findAll();
		request.setAttribute("allCustomers", list);
		RequestDispatcher rd = request.getRequestDispatcher("/lab01/showCustomers.jsp");
		rd.forward(request, response);
		return;		
	}

}
