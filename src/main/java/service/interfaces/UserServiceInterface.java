package service.interfaces;

import model.User;
import java.util.List;

/**
 * Class used for operating on the user records from the database and doing CRUD operations with it
 */
public interface UserServiceInterface {

    /**
     * Function to get a user by its username parameter
     * @param username the username we search in the database
     * @return User and it's details from the database
     */
    User getUserByUsername(String username);

    void addUser(User user);
}
