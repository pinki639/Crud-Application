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
import com.entity.Student;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String name=req.getParameter("name");    //write the name which we have given in jsp file
		String dob=req.getParameter("dob");
		String address=req.getParameter("address");
		String qualification=req.getParameter("qualification");
		String email=req.getParameter("email");
		
		
		
		//Student student=new Student(name,dob,address,qualification,email);
		
		Student student =new Student(name,dob,address,qualification,email);
		studentDAO dao=new studentDAO(DBConnect.getConn());
		
		HttpSession session=req.getSession();
		boolean f=dao.add_student(student);
		
		
		if(f) {
			//we write this session code to show message in add_student page when data is inserted
			session.setAttribute("succMsg", "student details submitted successfully");
			//we write here add_student because we want to print in add_student page
			resp.sendRedirect("add_student.jsp");
			
		}
		else {
			session.setAttribute("errorMsg", "something wrong with the server");
			//we write here add_student because we want to print in add_student page
			resp.sendRedirect("add_student.jsp");
		}
		
	}
	
}
