package service.implementation;

import model.Application;
import model.User;
import model.validator.MyException;
import model.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ApplicationRepoInterface;
import service.ServiceException;
import service.interfaces.ApplicationServiceInterface;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationServiceInterface {
    /**
     * The validator used for input validation.
     */
    @Autowired
    private Validator validator;
    /**
     * The repository for managing Application entities.
     */
    @Autowired
    private ApplicationRepoInterface applicationRepoInterface;

    @Override
    public void addApplication(Application application) throws MyException {
        validator.validateApplyToInternship(application);
        applicationRepoInterface.save(application);
    }

    @Override
    public List<Application> getApplicationsByUser(User user)  throws ServiceException {
        if (user.getId() == null || user.getId() <= 0) {
            throw new ServiceException("Invalid user ID provided.");
        }
        // Call the repository method to retrieve applications by user
        List<Application> applications = applicationRepoInterface.findAll();

        applications = applications.stream()
                .filter(application -> application.getApplicant().equals(user)) // Filter applications by the user
                .collect(Collectors.toList());

        if (applications.isEmpty()) {
            throw new ServiceException("No applications found for the given user.");
        }

        return applications;
    }
}
