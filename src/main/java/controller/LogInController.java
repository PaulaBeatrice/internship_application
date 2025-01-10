package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import security.jwttoken.JwtService;
import service.ServiceException;
import service.interfaces.AuthenticationServiceInterface;


/**
 * The controller used for request which implicate a user
 */
@Controller
@RequestMapping("/login")
@CrossOrigin
public class LogInController {

    /**
     * The Service used to authenticate a user
     */
    @Autowired
    private AuthenticationServiceInterface authenticationService;

    /**
     * The service used in order to generate an decode tokens
     */
    @Autowired
    private JwtService jwtService;

    /**
     * @param user: userul ce vrea sa fie logat
     * @return unauthorized - daca nu exista in db sau daca nu se matchuiesc parolele
     * @return jwtToken sub forma de string daca se matchuiesc
     * */
    @PostMapping("/auth")
    public @ResponseBody ResponseEntity<?> login(@RequestBody User user, HttpServletRequest request){
        if(authenticationService.logIn(user)){
            String token = jwtService.generateToken(user);
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
            return ResponseEntity.ok(token);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    /**
     * Registers a user
     * @param user UserDTO object containing the user's data
     * @return 200 OK - if the user was registered successfully
     * 400 BAD REQUEST - if the data is invalid
     */
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            authenticationService.signUp(user.getUsername(), user.getPassword(), user.getEmail(), user.getUserType());
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not register user.");
        }
    }
}
