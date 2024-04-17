package com.education.educationwebapp.teacher.repo;

import com.education.educationwebapp.common.CommonRepo;
import com.education.educationwebapp.common.MyDatabase;
import com.education.educationwebapp.student.entity.Student;
import com.education.educationwebapp.teacher.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepo implements CommonRepo<Teacher> {
    @Override
    public List<Teacher> getList(){
        List<Teacher>result=new ArrayList<>();

        try(Connection connection= MyDatabase.connect()){
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery("select * from teacher");
            while (resultSet.next()){
                Teacher teacher=new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setAge(resultSet.getInt("age"));


                result.add(teacher);
            }
        }
        catch(Exception ex){

            ex.printStackTrace();
        }
        return result;
    }
    @Override
    public void update(Teacher obj) {

        try (Connection connection=MyDatabase.connect()){
            connection.setAutoCommit(false);
            PreparedStatement statement=connection.prepareStatement("update teacher set name=?,surname=? email=?, " +
                    "age=?"+"where id=?");

            statement.setString(1,obj.getName());
            statement.setString(2, obj.getSurname());
            statement.setString(3, obj.getEmail());
            statement.setInt(4, obj.getAge());
            statement.setInt(6, obj.getId());
            statement.execute();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {

        try(Connection connection=MyDatabase.connect()) {
            PreparedStatement statement=connection.prepareStatement("delete from teacher where id=?");
            statement.setInt(1,id);
            statement.execute();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void insert(Teacher obj) {

        try (Connection connection=MyDatabase.connect()){

            PreparedStatement statement=connection.prepareStatement("insert into teacher(name, surname,email,age,salary)"
                    +"values(?,?,?,?,?)");

            statement.setString(1,obj.getName());
            statement.setString(2, obj.getSurname());
            statement.setString(3, obj.getEmail());
            statement.setInt(4, obj.getAge());
            statement.setDouble(5, obj.getSalary());
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Teacher findById(int id) {
        try(Connection connection=MyDatabase.connect()){
            PreparedStatement statement=connection.prepareStatement("select * from teacher where id=?");
            statement.setInt(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next()){

                Teacher teacher=new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setAge(resultSet.getInt("age"));
                teacher.setSalary(resultSet.getDouble("salary"));


                return teacher;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getList(String name, String surname, String email, String university, Integer age) {
        List<Teacher>result=new ArrayList<>();

        try(Connection connection= MyDatabase.connect()){
            PreparedStatement statement=connection.prepareStatement("select *"+
                    "from teacher where name=? or surname=?");
            statement.setString(1, name);
            statement.setString(2,surname);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()){
                Teacher teacher=new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setAge(resultSet.getInt("age"));


                result.add(teacher);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    }

