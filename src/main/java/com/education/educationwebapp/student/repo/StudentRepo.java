package com.education.educationwebapp.student.repo;

import com.education.educationwebapp.common.MyDatabase;
import com.education.educationwebapp.student.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class StudentRepo{

    public List<Student> getList(String name, String surname, String email, String university, Integer age, String password){
        List<Student>result=new ArrayList<>();
        int index=0;
String query="select * from student where 1=1 ";

if(name!=null && name.trim().length()>0){
    query +=" and name=?";
    index++;
}

        if(surname!=null && surname.trim().length()>0){
            query +=" and surname=?";
            index++;
        }

        if(email!=null && email.trim().length()>0){
            query +=" and email=?";
            index++;
        }

        if(university!=null && university.trim().length()>0){
            query +=" and university=?";
            index++;
        }

        if(age!=null && age>0){
            query +=" and age=?";
            index++;
        }
        if(password!=null && password.trim().length()>0){
            query +=" and password=?";
            index++;
        }
        try(Connection connection= MyDatabase.connect()){
            PreparedStatement statement=connection.prepareStatement(query);

            if(password!=null && password.trim().length()>0){
                statement.setString(index--, password);
            }

            if(age!=null && age>0){
                statement.setInt(index--, age);
            }
            if(university!=null && university.trim().length()>0){
                statement.setString(index--, university);
            }
            if(email!=null && email.trim().length()>0){
               statement.setString(index--, email);
            }
            if(surname!=null && surname.trim().length()>0){
                statement.setString(index--, surname);
            }
            if(name!=null && name.trim().length()>0){
               statement.setString(index, name);
            }

           ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()){


                result.add(fillStudent(resultSet));

            }

        }
        catch(Exception ex){

ex.printStackTrace();
        }
        return result;
    }
    public void update(Student obj)  {

        try (Connection connection=MyDatabase.connect()){

            PreparedStatement statement=connection.prepareStatement("update student set name=?,surname=?, email=?, " +
                    "age=?, university=? where id=?");

            statement.setString(1,obj.getName());
            statement.setString(2, obj.getSurname());
            statement.setString(3, obj.getEmail());
            statement.setInt(4, obj.getAge());
            statement.setString(5, obj.getUniversity());
            statement.setInt(6, obj.getId());
       statement.execute();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public void delete(Integer id) {
        try(Connection connection=MyDatabase.connect()) {
            PreparedStatement statement=connection.prepareStatement("delete from student where id=?");
            statement.setInt(1,id);
            statement.execute();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public void insert(Student obj) {
try (Connection connection=MyDatabase.connect()){

    PreparedStatement statement=connection.prepareStatement("insert into student(name, surname,email,age,university)"
            +"values(?,?,?,?,?)");

    statement.setString(1,obj.getName());
    statement.setString(2, obj.getSurname());
    statement.setString(3, obj.getEmail());
    statement.setInt(4, obj.getAge());
    statement.setString(5, obj.getUniversity());
statement.execute();
} catch (Exception ex) {
    ex.printStackTrace();
}
    }
    public Student findById(int id) {

        try(Connection connection=MyDatabase.connect()){
            PreparedStatement statement=connection.prepareStatement("select * from student where id=?");
            statement.setInt(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next()){
                return fillStudent(resultSet);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
return null;
    }
    public List<Student> getList(String name, String surname) {
        List<Student>result=new ArrayList<>();

        try(Connection connection= MyDatabase.connect()){
            PreparedStatement statement=connection.prepareStatement("select *"+
                    "from student where name=? or surname=?");
            statement.setString(1, name);
            statement.setString(2,surname);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()){
                result.add(fillStudent(resultSet));
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static Student fillStudent(ResultSet resultSet) throws Exception{

        Student student=new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setSurname(resultSet.getString("surname"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));
        student.setUniversity(resultSet.getString("university"));
        student.setPassword(resultSet.getString("password"));
        return student;
    }

}
