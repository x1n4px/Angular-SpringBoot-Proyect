package es.uma.informatica.sii.spring.jpa.demo.service;

import es.uma.informatica.sii.spring.jpa.demo.exception.ResourceNotFoundException;
import es.uma.informatica.sii.spring.jpa.demo.model.User;
import es.uma.informatica.sii.spring.jpa.demo.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repo;
    public User saveUser(User user){
        return repo.save(user);
    }

    public User findUserByEmailId(String email){
        return repo.findByEmailId(email);
    }

    public User fetchUserByEmailAndPassword(String email, String password){
        return repo.findByEmailIdAndPassword(email, password);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User usuario = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id : " + id));

        return ResponseEntity.ok((usuario));
    }




}
