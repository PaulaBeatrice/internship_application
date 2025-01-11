package service.interfaces;

import model.Internship;
import model.validator.MyException;
import java.util.List;

public interface InternshipServiceInterface {
    void addInternship(Internship internship) throws MyException;
    List<Internship> getAllInternships();
    void deleteInternship(Long internshipId);
}
