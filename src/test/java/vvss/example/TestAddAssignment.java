package vvss.example;

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

import java.util.Objects;

import static org.junit.Assert.fail;

public class TestAddAssignment {
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
    public void testCase1() {
        Tema temaToAdd = new Tema("10000", "Some description", 8, 6);

        try {
            service.addTema(temaToAdd);
            assert(true);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findTema("10000") != null);
    }

    @Test
    public void testCase2() {
        Tema temaToAdd = new Tema(null, "Some description", 8, 6);

        try {
            service.addTema(temaToAdd);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assert(true);
        }
    }

    @Test
    public void testCase3() {
        Tema temaToAdd = new Tema("", "Some description", 8, 6);

        try {
            service.addTema(temaToAdd);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assert(true);
        }
    }

    @Test
    public void testCase4() {
        Tema temaToAdd = new Tema("10001", "", 8, 6);

        try {
            service.addTema(temaToAdd);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assert(true);
        }
    }

    @Test
    public void testCase5() {
        Tema temaToAdd = new Tema("10002", null, 8, 6);

        try {
            service.addTema(temaToAdd);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assert(true);
        }
    }

    @Test
    public void testCase6() {
        Tema temaToAdd = new Tema("10003", "", 15, 6);

        try {
            service.addTema(temaToAdd);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assert(true);
        }
    }

    @Test
    public void testCase7() {
        Tema temaToAdd = new Tema("10004", "", 0, 6);

        try {
            service.addTema(temaToAdd);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assert(true);
        }
    }

    @Test
    public void testCase8() {
        Tema temaToAdd = new Tema("10005", "", 8, 15);

        try {
            service.addTema(temaToAdd);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assert(true);
        }
    }

    @Test
    public void testCase9() {
        Tema temaToAdd = new Tema("10006", "", 8, 0);

        try {
            service.addTema(temaToAdd);
            fail();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            assert(true);
        }
    }

    @Test
    public void testCase10() {
        Tema temaToAdd = new Tema("10007", "Some description", 8, 6);

        try {
            service.addTema(temaToAdd);
            assert(true);
            Tema temaToAdd2 = service.addTema(temaToAdd);
            assert(Objects.equals(temaToAdd2.getID(), temaToAdd.getID()));
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            fail();
        }

        assert(service.findTema("10007") != null);
    }
}