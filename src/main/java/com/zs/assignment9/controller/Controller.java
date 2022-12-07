package com.zs.assignment9.controller;

import com.zs.assignment9.repository.StudentDAOImpl;
import com.zs.assignment9.service.StudentServiceImpl;

public class Controller {
    public void studentController() throws Exception {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        StudentServiceImpl studentService = new StudentServiceImpl(studentDAO);
        studentDAO.initialData();
        studentService.addStudent(1, "sai", "iqbal");
        studentService.getStudent(1);

    }
}
