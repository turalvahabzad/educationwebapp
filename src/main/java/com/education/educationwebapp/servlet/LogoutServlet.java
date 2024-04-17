package com.education.educationwebapp.servlet;

import com.education.educationwebapp.student.entity.Student;
import com.education.educationwebapp.student.repo.StudentRepo;
import com.education.educationwebapp.util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

    private final StudentRepo repo=new StudentRepo();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
   request.getRequestDispatcher("logout.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("login");
    }


}