package com.zs.assignment9;

import com.zs.assignment9.dao.StudentDAOImpl;
import com.zs.assignment9.exception.BadRequestException;
import com.zs.assignment9.model.Student;
import com.zs.assignment9.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private static final Logger logger= LoggerFactory.getLogger(StudentServiceTest.class);
    @Mock
    private StudentDAOImpl studentDAO;

    @InjectMocks
    private StudentServiceImpl studentService;


    @Test
    void testAddStudent() throws BadRequestException {
        Student dummyStudent = new Student(1, "sam", "ram");
        Mockito.when(studentDAO.addStudent(Mockito.anyInt(), Mockito.anyString(),
                Mockito.anyString())).thenReturn(dummyStudent);
        Student actualStudent = studentService.addStudent(1, "sam", "ram");
        Assertions.assertEquals("sam", actualStudent.getFirstName());

    }

    @Test
    void testGetStudent() throws BadRequestException {
        Student dummyStudent = new Student(1, "rakesh", "kucharla");

        Mockito.when(studentDAO.fetchStudentById(Mockito.anyInt())).thenReturn(dummyStudent);
        Student actualStudent = studentService.getStudent(1);
        Assertions.assertEquals("rakesh", actualStudent.getFirstName());
    }

    @Test
    void testGetStudentException(){
        BadRequestException exception= Assertions.assertThrows(BadRequestException.class,()->{
            studentService.getStudent(-1);
        });
        Assertions.assertEquals("id is not valid",exception.getMessage());
    }

    @Test
    void testAddStudentIdException(){
        BadRequestException exception= Assertions.assertThrows(BadRequestException.class,()->{
            studentService.addStudent(-1,"sam","ram");
        });
        Assertions.assertEquals("id is not valid",exception.getMessage());
    }

    @Test
    void testAddStudentFirstNameException(){
        BadRequestException exception= Assertions.assertThrows(BadRequestException.class,()->{
            studentService.addStudent(1,null,"ram");
        });
        Assertions.assertEquals("First name is null",exception.getMessage());
    }

    @Test
    void testAddStudentLastNameException(){
        BadRequestException exception= Assertions.assertThrows(BadRequestException.class,()->{
            studentService.addStudent(1,"sam",null);
        });
        Assertions.assertEquals("Last name is null",exception.getMessage());
    }



}