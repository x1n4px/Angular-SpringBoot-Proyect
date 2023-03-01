package es.uma.informatica.sii.spring.jpa.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 200, nullable = false)
    private String emailId;
    @Column(length = 200, nullable = false)

    private String userName;
    @Column(length = 200, nullable = false)

    private String password;

    private String name;

    private String surname;
    private String address;
    private String telefone;

    public User() {
    }

    public User(Long id, String emailId, String userName, String password, String name, String surname, String address, String telefone) {
        super();
        this.id = id;
        this.emailId = emailId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
