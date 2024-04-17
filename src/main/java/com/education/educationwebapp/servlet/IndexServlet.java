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

@WebServlet(name="indexServlet", value = "/alma")

public class IndexServlet extends HttpServlet {

    private final StudentRepo repo=new StudentRepo();
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    final String name= ParamUtil.get(request.getParameter("name"));
    final String surname=ParamUtil.get(request.getParameter("surname"));
    final String email=ParamUtil.get(request.getParameter("email"));
    final String university=ParamUtil.get(request.getParameter("university"));
    final Integer age=ParamUtil.parseInt(request.getParameter("age"));
    List<Student> list=repo.getList(name, surname, email, university, age, null);

   request.getSession().setAttribute("savedList", list);
    request.setAttribute("studentList", list);

request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        final String name= ParamUtil.get(request.getParameter("name"));
        final String surname=ParamUtil.get(request.getParameter("surname"));
        final String email=ParamUtil.get(request.getParameter("email"));
        final String university=ParamUtil.get(request.getParameter("university"));
        final Integer age=ParamUtil.parseInt(request.getParameter("age"));
        final String command=ParamUtil.get(request.getParameter("command"));
        final Integer id=ParamUtil.parseInt(request.getParameter("id"));

        Student student=fillStudent(request);

        if (command.equalsIgnoreCase("insert")){
            repo.insert(student);
            response.sendRedirect("alma");
        }else if (command.equalsIgnoreCase("update")){
            repo.update(student);
            response.sendRedirect("alma");
        } else if (command.equalsIgnoreCase("delete")) {
              repo.delete(id);
              response.sendRedirect("alma");
      }
    }

      private static Student fillStudent(HttpServletRequest request){

          final String name= ParamUtil.get(request.getParameter("name"));
          final String surname=ParamUtil.get(request.getParameter("surname"));
          final String email=ParamUtil.get(request.getParameter("email"));
          final String university=ParamUtil.get(request.getParameter("university"));
          final Integer age=ParamUtil.parseInt(request.getParameter("age"));
          final Integer id=ParamUtil.parseInt(request.getParameter("id"));

              Student student=new Student();
              student.setId(id);
              student.setName(name);
              student.setSurname(surname);
              student.setEmail(email);
              student.setAge(age);
              student.setUniversity(university);
              return student;


    }
}

