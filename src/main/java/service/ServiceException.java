package service;

public class ServiceException extends Exception {
    public ServiceException(String invalidUsernameOrPassword) {
        super(invalidUsernameOrPassword);
    }
}
