package controller;

import model.Application;
import model.validator.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ServiceException;
import service.interfaces.ApplicationServiceInterface;


import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationServiceInterface applicationServiceInterface;

    @PostMapping("add/application")
    public @ResponseBody ResponseEntity<?> addAplication(@RequestBody Application application){
        try {
            applicationServiceInterface.addApplication(application);
            return ResponseEntity.ok(application);
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get/applications/{userId}")
    public @ResponseBody ResponseEntity<?> getApplications(@PathVariable Long userId) throws ServiceException {
        List<Application> applications = applicationServiceInterface.getApplicationsByUser(userId);
        return ResponseEntity.ok(applications);
    }
}
