package domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uzytkownik")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name"/*, nullable = false, unique = true*/, length = 50, columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    private LocalDateTime creationDate;

    @Enumerated(value = EnumType.ORDINAL)
    private UserType userType;

    public User() {
    }

    public User(String name, LocalDateTime creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public User(String name, LocalDateTime creationDate, UserType userType) {
        this.name = name;
        this.creationDate = creationDate;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
