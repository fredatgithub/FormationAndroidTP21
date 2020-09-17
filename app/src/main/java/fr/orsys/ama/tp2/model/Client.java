package fr.orsys.ama.tp2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * POJO pour conserver les données
 */
@Getter @Setter
@ToString

public class Client {
    protected String lastName;
    protected String firstName;
    protected Sexe sexe;
    protected String email;
    protected Integer age;
    protected Level level;
    protected String active;

    public Client(String lastName, String firstName, Sexe sexe, String email, Integer age, Level level, String active) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.sexe = sexe;
        this.email = email;
        this.age = age;
        this.level = level;
        this.active = active;
    }
}
