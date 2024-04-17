package com.education.educationwebapp.common;

import com.education.educationwebapp.student.entity.Student;

import java.util.List;

public interface CommonRepo<T>{

    public List<T>getList() ;
    public void update(T obj);
    public void delete(Integer id);

    public void insert(T obj);

    public T findById(int id);

    public List<Student> getList(String name,String surname,String email, String university,Integer age);
}
