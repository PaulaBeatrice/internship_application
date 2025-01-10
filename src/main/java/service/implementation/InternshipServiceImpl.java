package service.implementation;

import model.Internship;
import model.User;
import model.validator.MyException;
import model.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.InternshipRepoInterface;
import service.interfaces.InternshipServiceInterface;

import java.util.List;

@Service
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
        internshipRepoInterface.save(internship);
    }

    @Override
    public List<Internship> getAllInternships() {
        return internshipRepoInterface.findAll();
    }
}
