package br.com.deivisondfs2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.deivisondfs2.validation.LoginServiceValidation;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	LoginServiceValidation validationService = new LoginServiceValidation();
	
	String parameter = "";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		parameter = request.getParameter("name");
		
		//pasando value para o JSP
		request.setAttribute("name", parameter);
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);;
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		if (validationService.isValidUser(name, password)) {
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);			
		}else{
			request.setAttribute("errorMessage", "Login ou senha incorretos!!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
		
		
	}
}
