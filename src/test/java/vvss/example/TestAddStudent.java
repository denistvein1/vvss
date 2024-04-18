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

public class TestAddStudent {
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
    public void addStudentWithValidGrupa() {
        Student testStudent = new Student("69", "Test Student", 935, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findStudent("7890") != null);
    }

    @Test
    public void addStudentWithInvalidGrupa() {
        Student testStudent = new Student("69", "Test Student", -1, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assertTrue(true);
        }
    }
}
