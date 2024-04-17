<%@ page import="com.education.educationwebapp.util.ParamUtil" %>
<%@ page import="com.education.educationwebapp.student.entity.Student" %>
<%@ page import="com.education.educationwebapp.student.repo.StudentRepo" %>

<html>
<head>
    <title>Update</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
final Integer id= ParamUtil.parseInt(request.getParameter("id"));
    StudentRepo repo=new StudentRepo();
    final Student foundStudent=repo.findById(id);
%>

<form class="container" action="alma" method="POST">


        <div class="row  col-5">
            <label for="name" class="form-label">name </label>
            <input type="text" class="form-control" name="name" id="name" placeholder="name" value="<%=foundStudent.getName()%>">
        </div>

        <div class="row col-5">
            <label for="surname" class="form-label">surname </label>
            <input type="text" class="form-control" name="surname" id="surname" placeholder="surname" value="<%=foundStudent.getSurname()%>">
        </div>

        <div class="row col-5">
            <label for="age" class="form-label">age </label>
            <input type="text" class="form-control" name="age" id="age" placeholder="age" value="<%=foundStudent.getAge()%>">
        </div>
        <div class="row col-5">
            <label for="email" class="form-label">email </label>
            <input type="text" class="form-control" name="email" id="email" placeholder="email" value="<%=foundStudent.getEmail()%>">
        </div>

        <div class="row col-5">
            <label for="surname" class="form-label">university </label>

            <select class="form-select" name="university">
                <option value="ADNA" <%=foundStudent.getUniversity().equalsIgnoreCase("ADNA")?"selected":""%> >ADNA</option>
                <option value="BDU" <%=foundStudent.getUniversity().equalsIgnoreCase("BDU")?"selected":""%> >BDU</option>
                <option value="APU" <%=foundStudent.getUniversity().equalsIgnoreCase("APU")?"selected":""%>>APU</option>

            </select>

        </div>
        <input type="hidden" name="id" value="<%=foundStudent.getId()%>"/>
    <input type="hidden" name="command" value="update"/>

    <div class="row">
        <div class="col-3">
            <button type="submit" class="btn btn-primary mt-3">SAVE</button>
        </div>
    </div>

</form>

</body>
</html>
