package service.implementation;

import model.User;
import model.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.InternshipRepoInterface;
import repository.UserRepoInterface;
import service.interfaces.UserServiceInterface;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private Validator validator;

    @Autowired
    private UserRepoInterface userRepoInterface;

    @Override
    public List<User> getAllUsers() {
        // Implementarea metodei pentru obținerea tuturor utilizatorilor din baza de date
        return userRepoInterface.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        // Implementarea metodei pentru obținerea unui utilizator după username
        return userRepoInterface.findByUsername(username);
    }

    @Override
    public void addUser(User user) {
        userRepoInterface.save(user);
    }
}
