package service.interfaces;

import model.Application;
import model.validator.MyException;
import service.ServiceException;
import java.util.List;

public interface ApplicationServiceInterface {

    /**
     * Adds a new application.
     */
    void addApplication(Application application) throws MyException;

    /**
     * Retrieves all applications made by a specific user.
     *
     * @param user The user whose applications are to be fetched.
     * @return List of applications by the user.
     */
    List<Application> getApplicationsByUser(String user) throws ServiceException;

    void deleteApplication(Long applicationId);

    Application updateApplication(Application application, Application.ApplicationStatus status) throws ServiceException;
}
