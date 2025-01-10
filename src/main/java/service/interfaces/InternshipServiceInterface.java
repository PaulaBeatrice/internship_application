package service.interfaces;

import model.Internship;
import model.User;
import model.validator.MyException;

import java.time.LocalDate;
import java.util.List;

public interface InternshipServiceInterface {
    void addInternship(Internship internship) throws MyException;
    List<Internship> getAllInternships();
}
