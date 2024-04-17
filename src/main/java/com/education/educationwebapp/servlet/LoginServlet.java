package com.education.educationwebapp.servlet;

import com.education.educationwebapp.student.entity.Student;
import com.education.educationwebapp.student.repo.StudentRepo;
import com.education.educationwebapp.util.ParamUtil;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private final StudentRepo repo=new StudentRepo();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       final Object loggedInUser=request.getSession().getAttribute("loggedInUser");
       if(loggedInUser!=null){
           response.sendRedirect("alma");
           return;
       }
       request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email= ParamUtil.get(request.getParameter("email"));
        String password=ParamUtil.get(request.getParameter("password"));

        final List<Student>list=repo.getList(null, null, email, null, null, password);
        if (!list.isEmpty()){
            final Student student=list.get(0);
            request.getSession().setAttribute("loggedInUser", student);
        }
        response.sendRedirect("alma");
    }


}