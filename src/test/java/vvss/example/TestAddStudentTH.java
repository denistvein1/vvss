package vvss.example;

import domain.Student;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestAddStudentTH {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";
    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @Test
    public void testCase3() {
        Student testStudent = new Student("10002", "Test Student", -1, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testCase4() {
        Student testStudent = new Student("10003", "Test Student", 935, "");

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testCase5() {
        Student testStudent = new Student("10004", "Test Student", 935, null);

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testCase6() {
        Student testStudent = new Student("10005", "", 935, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testCase7() {
        Student testStudent = new Student("10006", null, 935, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }
    @Test
    public void testCase8() {
        Student testStudent = new Student("", "Test Student", 935, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testCase9() {
        Student testStudent = new Student(null, "Test Student", 935, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }

}
