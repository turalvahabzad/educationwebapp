<%@ page import="com.education.educationwebapp.student.repo.StudentRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.education.educationwebapp.student.entity.Student" %>
<%@ page import="com.education.educationwebapp.util.ParamUtil" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
  <title> Ana sehife</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>

<h1>Students</h1>

<%
  final String name= ParamUtil.get(request.getParameter("name"));
  final String surname=ParamUtil.get(request.getParameter("surname"));
  final String email=ParamUtil.get(request.getParameter("email"));
  final String university=ParamUtil.get(request.getParameter("university"));
  final Integer age=ParamUtil.parseInt(request.getParameter("age"));

%>
  <form class="container">
<div class="row">

  <div class="col-auto">
  <label for="name" class="form-label">name </label>
  <input type="text" class="form-control" name="name" id="name" placeholder="name" value="<%=name%>">
  </div>

  <div class="col-auto">
    <label for="surname" class="form-label">surname </label>
    <input type="text" class="form-control" name="surname" id="surname" placeholder="surname" value="<%=surname%>">
  </div>

  <div class="col-auto">
    <label for="age" class="form-label">age </label>
    <input type="text" class="form-control" name="age" id="age" placeholder="age" value="<%=age!=null?age:""%>">
  </div>
  <div class="col-auto">
    <label for="email" class="form-label">email </label>
    <input type="text" class="form-control" name="email" id="email" placeholder="email" value="<%=email%>">
  </div>

  <div class="col-auto">
  <label for="surname" class="form-label">university </label>

      <select class="form-select" name="university">
        <option value="ADNA" <%=university!=null && university.equals("ADNA")?"selected":""%> >ADNA</option>
        <option value="BDU" <%=university!=null && university.equals("BDU")?"selected":""%> >BDU</option>
        <option value="APU" <%=university!=null && university.equals("APU")?"selected":""%> >APU</option>

      </select>

  </div>
  </div>
<div class="row">
  <div class="col-1">
  <button type="submit" class="btn btn-primary mt-3">SEARCH</button>
</div>

  <div class="col-1">
    <a type="submit" class="btn btn-primary mt-3" href="insert.jsp">Insert</a>
  </div>
</div>

</form>
<table style="width: 100%">
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Surname</th>
    <th>Age</th>
    <th>Email</th>
    <th>Actions</th>

  </tr>
  </thead>
  <tbody>
  <%
    List<Student> list=(List<Student>)request.getAttribute("studentList");
    for(int i=0;i<list.size();i++){
      Student student=list.get(i);
  %>


  <tr <%=(i%2==0? "class=\"table-secondary\"":"") %>>
    <td>
    <%=student.getId() %>
    </td>
    <td>
      <%=student.getName() %>
    </td>
    <td>
      <%=student.getSurname()%>

    </td>
    <td>
      <%=student.getAge() %>
    </td>

    <td>
      <%=student.getEmail() %>
    </td>

    <td>
      <a href="delete.jsp?id=<%=student.getId()%>" class="btn btn-danger">Delete</a>
      <a href="update.jsp?id=<%=student.getId()%>" class="btn btn-info">Update</a>
    </td>
  </tr>

  <%}%>

  </tbody>
</table>
</body>

</html>

