package service.implementation;

import model.Internship;
import model.User;
import model.validator.MyException;
import model.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import repository.InternshipRepoInterface;
import service.interfaces.InternshipServiceInterface;

import java.time.LocalDate;
import java.util.List;

public class InternshipServiceImpl implements InternshipServiceInterface {
    /**
     * The validator used for input validation.
     */
    @Autowired
    private Validator validator;
    /**
     * The repository for managing Internship entities.
     */
    @Autowired
    private InternshipRepoInterface internshipRepoInterface;

    @Override
    public void addInternship(Internship internship) throws MyException {
        validator.validateAddInternship(internship);

    }

    @Override
    public List<Internship> getAllInternships() {
        return internshipRepoInterface.findAll();
    }
}
