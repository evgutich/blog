package com.leverx.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    @Length(min = 2, message = "*Your first name must have at least 2 characters")
    @NotBlank(message = "*Please provide your first name")
    private String firstName;

    @Column(name = "last_name")
    @Length(min = 2, message = "*Your last name must have at least 2 characters")
    @NotBlank(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "password")
    @Length(min = 4, message = "*Your password must have at least 4 characters")
    @NotBlank(message = "*Please provide your password")
    private String password;

    @Column(name = "email", unique = true)
    @Email(message = "*Please provide a valid email")
    @NotBlank(message = "*Please provide your email")
    private String email;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    private Boolean active = false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles = new HashSet<>();
}
