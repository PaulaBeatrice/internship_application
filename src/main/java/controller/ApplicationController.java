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
    public @ResponseBody ResponseEntity<?> addApplication(@RequestBody Application application){
        try {
            applicationServiceInterface.addApplication(application);
            return ResponseEntity.ok(application);
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get/application/{username}")
    public @ResponseBody ResponseEntity<?> getApplications(@PathVariable String username) throws ServiceException {
        List<Application> applications = applicationServiceInterface.getApplicationsByUser(username);
        return ResponseEntity.ok(applications);
    }

    @DeleteMapping("/delete/application/{applicationId}")
    public @ResponseBody ResponseEntity<?> deleteApplication(@PathVariable Long applicationId) {
        applicationServiceInterface.deleteApplication(applicationId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/screening/application/{applicationId}")
    public @ResponseBody ResponseEntity<?> updateApplicationToScreening(@PathVariable Long applicationId, @RequestBody Application application) throws ServiceException {
        application.setId(applicationId);
        return ResponseEntity.ok(applicationServiceInterface.updateApplication(application, Application.ApplicationStatus.SCREENING));
    }

    @PutMapping("/accepted/application/{applicationId}")
    public @ResponseBody ResponseEntity<?> updateApplicationToAccepted(@PathVariable Long applicationId, @RequestBody Application application) throws ServiceException {
        application.setId(applicationId);
        return ResponseEntity.ok(applicationServiceInterface.updateApplication(application, Application.ApplicationStatus.ACCEPTED));
    }

    @PutMapping("/rejected/application/{applicationId}")
    public @ResponseBody ResponseEntity<?> updateApplicationToRejected(@PathVariable Long applicationId, @RequestBody Application application) throws ServiceException {
        application.setId(applicationId);
        return ResponseEntity.ok(applicationServiceInterface.updateApplication(application, Application.ApplicationStatus.REJECTED));
    }

    @PutMapping("/retired/application/{applicationId}")
    public @ResponseBody ResponseEntity<?> updateApplicationToRetired(@PathVariable Long applicationId, @RequestBody Application application) throws ServiceException {
        application.setId(applicationId);
        return ResponseEntity.ok(applicationServiceInterface.updateApplication(application, Application.ApplicationStatus.RETIRED));
    }
}
