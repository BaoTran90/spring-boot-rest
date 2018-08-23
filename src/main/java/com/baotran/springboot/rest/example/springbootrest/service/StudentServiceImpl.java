package com.baotran.springboot.rest.example.springbootrest.service;

import com.baotran.springboot.rest.example.springbootrest.common.exceptions.NotFoundException;
import com.baotran.springboot.rest.example.springbootrest.model.Student;
import com.baotran.springboot.rest.example.springbootrest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> find() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new NotFoundException("Student id-" + id + " not found.");

        return student.get();
    }

    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void update(long id, Student student) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            throw new NotFoundException("Student id-" + id + " not found.");

        student.setId(id);

        studentRepository.save(student);
    }
}
