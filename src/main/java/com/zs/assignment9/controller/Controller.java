package com.zs.assignment9.controller;

import com.zs.assignment9.model.Student;
import com.zs.assignment9.repository.StudentDAOImpl;
import com.zs.assignment9.service.StudentServiceImpl;

public class Controller {
    public void studentController() throws Exception {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        StudentServiceImpl studentService = new StudentServiceImpl(studentDAO);
        studentDAO.initialData();
        Student student = studentService.addStudent(1, "sai", "iqbal");
        Student student1 = studentService.getStudent(1);

    }
}
