package com.zs.assignment9.controller;

import com.zs.assignment9.exception.BadRequestException;
import com.zs.assignment9.service.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentController {
   private static final Logger logger= LoggerFactory.getLogger(StudentController.class);
    private final StudentServiceImpl studentService;

    public StudentController() {
        this.studentService = new StudentServiceImpl();
    }

    public void start() {
        try {
            studentService.addStudent(3, "sai", "iqbal");
            studentService.getStudent(2);
        } catch (BadRequestException e) {
            logger.error("Something went wrong :" + e.getMessage());
        }

    }
}
