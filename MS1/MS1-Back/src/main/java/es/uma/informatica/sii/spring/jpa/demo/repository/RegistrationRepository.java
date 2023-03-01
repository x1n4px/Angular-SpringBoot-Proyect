package es.uma.informatica.sii.spring.jpa.demo.repository;

import es.uma.informatica.sii.spring.jpa.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {
    public User findByEmailId(String emailId);
    public User findByEmailIdAndPassword(String emailId, String password);

}
