package com.zs.assignment9.repository;


import com.zs.assignment9.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl  implements StudentDAO {
    private final List<Student> studentList = new ArrayList<>();
    @Override
    public Student addStudent(int id, String firstName, String lastName) {
        Student student = new Student(id, firstName, lastName);
        studentList.add(student);
        return student;
    }

    @Override
    public Student fetchStudentById(int id) {
        return studentList.get(id - 1);
    }

    public void initialData() {
        studentList.add(new Student(1, "sai", "kumar"));
        studentList.add(new Student(2, "ali", "iqbal"));
    }

}
