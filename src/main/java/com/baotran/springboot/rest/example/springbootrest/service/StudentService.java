package com.baotran.springboot.rest.example.springbootrest.service;

import com.baotran.springboot.rest.example.springbootrest.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> find();
    public Student getById(long id);
    public void deleteById(long id);
    public Student create(Student student);
    public void update(long id, Student student);
}
