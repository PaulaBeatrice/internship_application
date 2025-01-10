package controller;

import model.Application;
import model.Internship;
import model.User;
import model.validator.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ServiceException;
import service.interfaces.ApplicationServiceInterface;
import service.interfaces.InternshipServiceInterface;
import service.interfaces.UserServiceInterface;

import java.util.List;

public class ApplicationController {
    @Autowired
    private ApplicationServiceInterface applicationServiceInterface;

    @Autowired
    private UserServiceInterface userServiceInterface;

    @PostMapping("add/application")
    public @ResponseBody ResponseEntity<?> addAplication(Application application){
        try {
            applicationServiceInterface.addApplication(application);
            return ResponseEntity.ok(application);
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get/applications")
    public @ResponseBody ResponseEntity<?> getApplications(User user) throws ServiceException {
        List<Application> applications = applicationServiceInterface.getApplicationsByUser(user);
        return ResponseEntity.ok(applications);
    }
}
