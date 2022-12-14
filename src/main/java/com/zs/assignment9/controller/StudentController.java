package com.zs.assignment9.controller;

import com.zs.assignment9.dao.StudentDAOImpl;
import com.zs.assignment9.exception.BadRequestException;
import com.zs.assignment9.service.StudentServiceImpl;

public class StudentController {
    private final StudentDAOImpl studentDAO;
    private final StudentServiceImpl studentService;

    public StudentController() {
        this.studentDAO = new StudentDAOImpl();
        this.studentService = new StudentServiceImpl();
        studentDAO.initialData();
    }

    public void start() {
        try {
            studentService.addStudent(1, "sai", "iqbal");
            studentService.getStudent(1);
        } catch (BadRequestException e) {
            throw new RuntimeException("Something went wrong :" + e.getMessage());
        }

    }
}
