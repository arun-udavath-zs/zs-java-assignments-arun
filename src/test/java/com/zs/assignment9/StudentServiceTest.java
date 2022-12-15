package com.zs.assignment9;

import com.zs.assignment9.dao.StudentDAOImpl;
import com.zs.assignment9.exception.BadRequestException;
import com.zs.assignment9.model.Student;
import com.zs.assignment9.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentDAOImpl studentDAO;

    @InjectMocks
    private StudentServiceImpl studentService;


    static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.arguments(1,"sam","ram",false),
                Arguments.arguments(-1,"sam","ram", true),
                Arguments.arguments(1,null,"sam",true),
                Arguments.arguments(1,"sam",null,true)
        );
    }


    @Test
    void GetStudent() throws BadRequestException {
        Student dummyStudent = new Student(1, "rakesh", "kucharla");

        Mockito.when(studentDAO.fetchStudentById(Mockito.anyInt())).thenReturn(dummyStudent);
        Student actualStudent = studentService.getStudent(1);
        Assertions.assertEquals("rakesh", actualStudent.getFirstName());
    }

    @Test
    void GetStudentException(){
        BadRequestException exception= Assertions.assertThrows(BadRequestException.class,()->{
            studentService.getStudent(-1);
        });
        Assertions.assertEquals("id is not valid",exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getData")
    void addStudent(int id, String fName, String lName, boolean isExpected) {
        if(isExpected) {
           BadRequestException exception= Assertions.assertThrows(BadRequestException.class,()->{
                studentService.addStudent(1,"sam",null);});
            Assertions.assertEquals("input provided is not valid",exception.getMessage());
        }
        else {
            Student dummyStudent = new Student(1, "sam", "ram");
            Mockito.when(studentDAO.addStudent(Mockito.anyInt(), Mockito.anyString(),
                    Mockito.anyString())).thenReturn(dummyStudent);
            Student actualStudent = null;
            try {
                actualStudent = studentService.addStudent(1, "sam", "ram");
            } catch (BadRequestException e) {
              Assertions.fail(e.getMessage());
            }
            Assertions.assertEquals("sam", actualStudent.getFirstName());
        }

    }
}