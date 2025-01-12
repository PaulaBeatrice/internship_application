package model.validator;

import model.Application;
import model.Internship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Component class responsible for validating Internship and Application objects.
 */
@Component
public class Validator {

    /**
     * Validates adding a new internship.
     * @param internship The internship to be validated.
     * @throws MyException If validation fails, an exception with error messages is thrown.
     */
    public void validateAddInternship(Internship internship) throws MyException {
        List<String> errorMessages = new ArrayList<>();

        if (internship.getTitle() == null || internship.getTitle().trim().isEmpty()) {
            errorMessages.add("The title cannot be empty!");
        }
        if (internship.getDescription() == null || internship.getDescription().trim().isEmpty()) {
            errorMessages.add("The description cannot be empty!");
        }
        if (internship.getLocation() == null || internship.getLocation().trim().isEmpty()) {
            errorMessages.add("The location cannot be empty!");
        }
        if (internship.getApplicationDeadline() == null || internship.getApplicationDeadline().isBefore(java.time.LocalDate.now())) {
            errorMessages.add("The application deadline must be a future date!");
        }
        if (internship.getField() == null || internship.getField().trim().isEmpty()) {
            errorMessages.add("The field cannot be empty!");
        }

        if (!errorMessages.isEmpty()) {
            throw new MyException(String.join("\n", errorMessages));
        }
    }

    /**
     * Validates applying to an internship (creating an application).
     * @param application The application to be validated.
     * @throws MyException If validation fails, an exception with error messages is thrown.
     */
    public void validateApplyToInternship(Application application) throws MyException {
        List<String> errorMessages = new ArrayList<>();

        if (application.getApplicant() == null) {
            errorMessages.add("The applicant cannot be null!");
        }
        if (application.getInternship() == null) {
            errorMessages.add("The internship cannot be null!");
        }
        if (application.getApplicationDate() == null || application.getApplicationDate().isBefore(java.time.LocalDate.now())) {
            errorMessages.add("The application date must be today or a future date!");
        }

        if (!errorMessages.isEmpty()) {
            throw new MyException(String.join("\n", errorMessages));
        }
    }
}
