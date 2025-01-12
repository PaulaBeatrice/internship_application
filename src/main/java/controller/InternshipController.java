package controller;

import model.Internship;
import model.validator.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.interfaces.InternshipServiceInterface;

import java.util.List;

@Controller
@RequestMapping("/internship")
@CrossOrigin
public class InternshipController {
    @Autowired
    private InternshipServiceInterface internshipServiceInterface;

    /**
     * Retrieves all internships.
     * @return ResponseEntity with a list of all internships or an empty list if none are found.
     */
    @GetMapping("/get/internships")
    public @ResponseBody ResponseEntity<?> getInternships() {
        List<Internship> internships = internshipServiceInterface.getAllInternships();
        return ResponseEntity.ok(internships);
    }

    @PostMapping("add/internship")
    public @ResponseBody ResponseEntity<?> addInternship(@RequestBody Internship internship){
        try {
            internshipServiceInterface.addInternship(internship);
            return ResponseEntity.ok(internship);
        } catch (MyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("delete/internship/{internshipId}")
    public @ResponseBody ResponseEntity<?> deleteInternship(@PathVariable Long internshipId){
        try {
            internshipServiceInterface.deleteInternship(internshipId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

}
