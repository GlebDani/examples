package ru.danilenko.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.danilenko.Enum.Roles;

/**
 * Entity for user
 */
@Setter
@Getter
@AllArgsConstructor
public class User {

    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Roles role;

}
