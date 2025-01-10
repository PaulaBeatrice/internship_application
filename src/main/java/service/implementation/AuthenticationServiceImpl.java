package service.implementation;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ServiceException;
import service.interfaces.AuthenticationServiceInterface;
import service.interfaces.UserServiceInterface;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Implementation of the AuthenticationService class
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationServiceInterface {
    /**
     * Parameter use in order to access the records from the database
     */
    @Autowired
    private UserServiceInterface userService;

    /**
     *  Function to check if a user exists in the database
     * @param user The user who wants log in the application, has to have username and password
     * @return true the username and password match with the ones from the database
     *         false else
     */
    @Override
    public boolean logIn(User user) {
        User dbUser = userService.getUserByUsername(user.getUsername());
        if(dbUser == null){
            return false;
        }
        return Objects.equals(dbUser.getPassword(), user.getPassword());
    }
    /**
     * Function to add a new user in the database
     * @param username the username of the new user
     * @param password the password for the new user
     * @param email email for the new user
     * @param type type for the new user
     * @throws ServiceException if username already exists or the username contains something beside letters, numbers and '_'
     */
    @Override
    public void signUp(String username, String password, String email, User.UserType type) throws ServiceException {
        User user = userService.getUserByUsername(username);
        if(user != null)
            throw new ServiceException("User already exists.");

        if(!username.matches("[a-zA-Z0-9_]+")){
            throw new ServiceException("Username can only contain letters, numbers and _.");
        }

        userService.addUser(username, password, email, type);
    }
}
