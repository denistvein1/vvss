package vvss.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class IntegrationTest {
    //
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
    public void addStudentValid() {
        Student testStudent = new Student("19", "Test Student", 935, "teststudent@gmail.com");

        try {
            service.addStudent(testStudent);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findStudent("19") != null);
    }

    @Test
    public void addTemaValid() {
        Tema temaToAdd = new Tema("19", "Some description", 9, 6);

        try {
            service.addTema(temaToAdd);
            assert(true);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findTema("19") != null);
    }

    @Test
    public void addGradeValid()
    {
        Nota nota = new Nota("19", "19", "19", 10, LocalDate.parse("2024-05-30"));

        try {
            service.addNota(nota, "good");
            assert(true);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }
    }

    @Test
    public void integratedAddStudentAssignmentAndGrade() {
        Student testStudent = new Student("24", "Jane Doe", 935, "janedoe@gmail.com");
        Tema newAssignment = new Tema("24", "Integration Testing Assignment", 8, 6);
        Nota newGrade = new Nota("102", "24", "24", 9.5, LocalDate.parse("2024-05-30"));

        try {
            service.addStudent(testStudent);
            Student addedStudent = service.findStudent("24");
            assert(addedStudent != null);

            service.addTema(newAssignment);
            Tema addedAssignment = service.findTema("24");
            assert(addedAssignment != null);

            service.addNota(newGrade, "Excellent work!");
            System.out.println("Integration test passed: Student, Assignment, and Grade added successfully.");
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail("Integration test failed due to validation error.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Integration test failed due to an unexpected error.");
        }
    }
}
