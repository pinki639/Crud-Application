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
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String name=req.getParameter("name");    //write the name which we have given in jsp file
		String dob=req.getParameter("dob");
		String address=req.getParameter("address");
		String qualification=req.getParameter("qualification");
		String email=req.getParameter("email");
		int id=Integer.parseInt(req.getParameter("id"));//we do so because by default is will be in string so we have to convert into int
		// and make a new constructor by selection id and all field
		
		
		
		
		Student student =new Student(id,name,dob,address,qualification,email);
		studentDAO dao=new studentDAO(DBConnect.getConn());
		
		HttpSession session=req.getSession();
		 boolean f=dao.update_student(student);
		 
		 if(f) {
					session.setAttribute("succMsg", "student details update successfully");
					resp.sendRedirect("index.jsp");
				
			}
			else {
				session.setAttribute("errorMsg", "something wrong with the server not able to update");
			    resp.sendRedirect("index.jsp");
			}
			
		}
		
	}


