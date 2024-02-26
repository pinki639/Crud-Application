package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conn.DBConnect;
import com.dao.studentDAO;
@WebServlet("/delete")
public class DeleteServlate  extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	studentDAO dao=new studentDAO(DBConnect.getConn());
	boolean f=dao.deleteStudent(id);
	HttpSession session =req.getSession();
	
	
	if(f) {
		//we write this session code to show message in add_student page when data is inserted
		session.setAttribute("succMsg", "student details deleted successfully");
		//we write here add_student because we want to print in add_student page
		resp.sendRedirect("index.jsp");
		
	}
	else {
		session.setAttribute("errorMsg", "something wrong with the server");
		//we write here add_student because we want to print in add_student page
		resp.sendRedirect("index.jsp");
	}
}
}
