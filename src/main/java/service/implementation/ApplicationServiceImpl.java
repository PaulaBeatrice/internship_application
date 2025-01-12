package service.implementation;

import model.Application;
import model.validator.MyException;
import model.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ApplicationRepoInterface;
import repository.UserRepoInterface;
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

    @Autowired
    private UserRepoInterface userRepoInterface;

    @Override
    public void addApplication(Application application) throws MyException {
        validator.validateApplyToInternship(application);
        application.setStatus(Application.ApplicationStatus.PENDING);
        application.setApplicant(userRepoInterface.findByUsername(application.getApplicant().getUsername()));
        applicationRepoInterface.save(application);
    }

    @Override
    public List<Application> getApplicationsByUser(String username)  throws ServiceException {
        if (username == null || username.isEmpty()) {
            throw new ServiceException("Invalid username provided.");
        }
        // Call the repository method to retrieve applications by user
        List<Application> applications = applicationRepoInterface.findAll();

        applications = applications.stream()
                .filter(application -> application.getApplicant().getUsername().equals(username)) // Filter applications by the user
                .collect(Collectors.toList());

        if (applications.isEmpty()) {
            applications = applicationRepoInterface.findAll();
            applications = applications.stream()
                    .filter(application -> application.getInternship().getRecruiter().getUsername().equals(username)) // Filter applications by the user
                    .collect(Collectors.toList());

            if (applications.isEmpty()) {
                throw new ServiceException("No applications found for the given user.");
            }
        }

        return applications;
    }

    @Override
    public void deleteApplication(Long applicationId) {
        applicationRepoInterface.deleteById(applicationId);
    }

    @Override
    public Application updateApplication(Application application, Application.ApplicationStatus status) throws ServiceException {
        Application application1 = applicationRepoInterface.findById(application.getId()).orElse(null);
        if (application1 != null) {
            application1.setStatus(status);
            applicationRepoInterface.save(application1);
            return application1;
        } else {
            throw new ServiceException("The application id did not match");
        }
    }
}
