package com.zs.assignment9.service;

import com.zs.assignment9.model.Student;

public interface StudentService {

    Student addStudent(int id, java.lang.String firstName, java.lang.String lastName) throws Exception;

    Student getStudent(int id);
}
