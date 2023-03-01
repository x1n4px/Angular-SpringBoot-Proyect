package es.uma.informatica.sii.spring.jpa.demo.controller;

import es.uma.informatica.sii.spring.jpa.demo.exception.ResourceNotFoundException;
import es.uma.informatica.sii.spring.jpa.demo.model.User;
import es.uma.informatica.sii.spring.jpa.demo.repository.RegistrationRepository;
import es.uma.informatica.sii.spring.jpa.demo.service.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    @Autowired
    private RegistrationService service;
    @Autowired
    private RegistrationRepository repo;




    @PostMapping ("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("Controller Called");
        //Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //String hash = argon2.hash(1, 1024, 1, user.getPassword());
      //  user.setPassword(hash);
        return ResponseEntity.ok(repo.save(user));
    }

    @PostMapping("/login")
    public User loginUser(HttpServletRequest request, @RequestBody User user) throws Exception {
        String tempEmailId = user.getEmailId();
        String tempPass = user.getPassword();
        User userObj = null;
        if(tempEmailId != null && tempPass != null){
            userObj = service.fetchUserByEmailAndPassword(tempEmailId, tempPass);
        }

        //Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //boolean laPasswordEsLaMisma = argon2.verify(tempPass, user.getPassword());

        if(userObj == null){
            throw new Exception("Bad credentials");
        }
        //Guardamos el usuario en la sesion actual
        HttpSession session = request.getSession();
        session.setAttribute("user", userObj);
        return userObj;
    }


    @GetMapping("/profile")
    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        return currentUser;
    }




    @GetMapping("/profile")
    public String  getUserDetails(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("emailId");
        if (user != null) {
            return "Welcome, " + user.getEmailId();
        } else {
            return "Please log in first.";
        }
    }

}
