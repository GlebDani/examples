package com.example.baseline.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Email(message = "Invalid email")
    @NotEmpty(message = "Email missing")
    private String email;

    @Column
    @NotEmpty(message = "First name missing")
    private String firstName;

    @Column
    @NotEmpty(message = "Last name missing")
    private String lastName;

    @Column
    private String gender;

    @Column
    @Min(value=18)
    @Max(value=99)
    private int age;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column
    private String photo;

    @Column
    private String role;

    @Column
    @NotEmpty(message = "Password missing")
    private String password;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @Column
    private String token;

    @Column
    private LocalDateTime password_reset_token_expity;
    @ManyToMany
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="authority_id", referencedColumnName = "id")})
    private Set<Authority> authoritySet= new HashSet<>();

}
