package controller;

import model.Internship;
import model.validator.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.interfaces.InternshipServiceInterface;
import service.interfaces.UserServiceInterface;

import java.util.List;

@Controller
@RequestMapping("/intenship")
public class InternshipController {
    @Autowired
    private InternshipServiceInterface internshipServiceInterface;

    @Autowired
    private UserServiceInterface userServiceInterface;

    /**
     * Retrieves all internships.
     * @return ResponseEntity with a list of all internships or an empty list if none are found.
     */
    @GetMapping("/get/internships")
    public @ResponseBody ResponseEntity<?> getQuizzes() {
        List<Internship> internships = internshipServiceInterface.getAllInternships();
        return ResponseEntity.ok(internships);
    }

    @PostMapping("add/internship")
    public @ResponseBody ResponseEntity<?> addInternship(Internship internship){
        try {
            internshipServiceInterface.addInternship(internship);
            return ResponseEntity.ok(internship);
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

}
