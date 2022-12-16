package com.zs.assignment9.dao;


import com.zs.assignment9.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDAOImpl.class);
    private final List<Student> studentList;

    public StudentDAOImpl() {
        studentList = new ArrayList<>();
    }

    @Override
    public Student addStudent(int id, String firstName, String lastName) {
        Student student = new Student(id, firstName, lastName);
        studentList.add(student);
        LOGGER.info("student details added in the database");
        return student;
    }

    @Override
    public Student fetchStudentById(int id) {
        return studentList.get(id - 1);
    }

    public void initialData() {
        LOGGER.info("initial setup of database");
        studentList.add(new Student(1, "sai", "kumar"));
        studentList.add(new Student(2, "ali", "iqbal"));
    }

}
