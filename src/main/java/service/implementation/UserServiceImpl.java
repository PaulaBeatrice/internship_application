package service.implementation;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepoInterface;
import service.interfaces.UserServiceInterface;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepoInterface userRepoInterface;

    @Override
    public User getUserByUsername(String username) {
        return userRepoInterface.findByUsername(username);
    }

    @Override
    public void addUser(User user) {
        userRepoInterface.save(user);
    }
}
