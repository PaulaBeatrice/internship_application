package service.interfaces;

import model.User;
import service.ServiceException;


/**
 * Interface used for authenticating the user or signing up a new user*/
public interface AuthenticationServiceInterface {
    /**
     *  Function to check if a user exists in the database
     * @param user The user who wants log in the application, has to have username and password
     * @return true the username and password match with the ones from the database
     *         false else
     */
    User logIn(User user);

    /**
     * Function to add a new user in the database
     * @param username the username of the new user
     * @param password the password for the new user
     * @param email email for the new user
     * @param type the type of the new user
     * @throws ServiceException if username already exists or the username contains something beside letters, numbers and '_'
     */
    void signUp(String username, String password, String email, User.UserType type) throws ServiceException;
}
