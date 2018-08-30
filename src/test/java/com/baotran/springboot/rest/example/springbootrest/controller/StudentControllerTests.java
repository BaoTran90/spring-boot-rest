package com.baotran.springboot.rest.example.springbootrest.controller;

import com.baotran.springboot.rest.example.springbootrest.SpringBootRestApplication;
import com.baotran.springboot.rest.example.springbootrest.SpringBootRestApplicationTests;
import com.baotran.springboot.rest.example.springbootrest.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class StudentControllerTests extends SpringBootRestApplicationTests {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getStudentsList() throws Exception {
        String uri = "/students";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Student[] students = super.mapFromJson(content, Student[].class);
        assertTrue(students.length > 0);
    }

    @Test
    public void postStudent() throws Exception {
        String uri = "/students";
        Student newStudent = new Student("Bao Tran", "ABC123");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(super.mapToJson(newStudent))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @Test
    public void whenValidID_thenStudentShouldBeFound() throws Exception {
        String uri = "/students/10001";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        Student foundStudent = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Student.class);
        assertEquals((long)foundStudent.getId(), 10001L);
    }

    @Test
    public void whenInValidID_thenStudentShouldNotBeFound() throws Exception {
        String uri = "/students/100010";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Student id-100010 not found.");
    }

    @Test
    public void deleteStudent() throws Exception {
        String uri = "/students/10002";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void whenValidID_thenStudentShouldBeUpdated() throws Exception {
        // update student
        String uri = "/students/10001";
        Student updateStudent = new Student("Bao Tran", "ABC123");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(updateStudent))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);

        // verify updated student data
        uri = "/students/10001";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        Student updatedStudent = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Student.class);
        assertEquals(updatedStudent.getName(), updateStudent.getName());
        assertEquals(updatedStudent.getPassportNumber(), updateStudent.getPassportNumber());
    }

    @Test
    public void whenInValidID_thenStudentShouldNotBeUpdated() throws Exception {
        String uri = "/students/100010";
        Student updateStudent = new Student("Bao Tran", "ABC123");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(updateStudent))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Student id-100010 not found.");
    }
}
