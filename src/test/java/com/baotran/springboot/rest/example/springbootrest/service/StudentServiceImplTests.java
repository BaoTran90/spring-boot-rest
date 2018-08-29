package com.baotran.springboot.rest.example.springbootrest.service;

import com.baotran.springboot.rest.example.springbootrest.common.exceptions.NotFoundException;
import com.baotran.springboot.rest.example.springbootrest.model.Student;
import com.baotran.springboot.rest.example.springbootrest.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentServiceImplTests {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @MockBean
    private StudentRepository studentRepository;

    private Student student = new Student(1L, "Bao Tran", "ABC123");

    @Test
    public void shouldInvokeDeleteByIdMethodOfStudentRepo() {
        studentServiceImpl.deleteById(12345L);
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(12345L);
    }

    @Test
    public void shouldInvokeFindAllMethodOfStudentRepo() {
        studentServiceImpl.find();
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void whenValidId_thenStudentShouldBeFound() {
        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Student result = studentServiceImpl.getById(student.getId());
        Assert.assertEquals(result, student);
    }

    @Test(expected = NotFoundException.class)
    public void whenInValidId_thenErrorShouldBeThrown() {
        Mockito.when(studentRepository.findById(2L)).thenReturn(Optional.empty());
        studentServiceImpl.getById(2L);
    }

    @Test
    public void shouldInvokeSaveMethodOfStudentRepo() {
        Student newStudent = new Student("Trang Vo", "ABC123");
        studentServiceImpl.create(newStudent);
        Mockito.verify(studentRepository, Mockito.times(1)).save(newStudent);
    }

    @Test
    public void whenValidId_thenStudentShouldBeUpdated() {
        Student updatedStudent = new Student("Hoang Nguyen", "ABC123");
        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        studentServiceImpl.update(student.getId(), updatedStudent);

        Mockito.verify(studentRepository, Mockito.times(1)).findById(student.getId());
        Mockito.verify(studentRepository, Mockito.times(1)).save(updatedStudent);
    }

    @Test(expected = NotFoundException.class)
    public void whenInValidId_thenNotFoundErrorShouldBeThrown() {
        Student updatedStudent = new Student("Hoang Nguyen", "ABC123");
        Mockito.when(studentRepository.findById(2L)).thenReturn(Optional.empty());
        studentServiceImpl.update(2L, updatedStudent);
    }

}
