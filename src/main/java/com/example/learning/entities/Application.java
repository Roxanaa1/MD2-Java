package com.example.learning.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity(name = "application")
@Table(name = "APPLICATION")
public class Application {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @ManyToMany(mappedBy = "applications", fetch = FetchType.EAGER)
    private List<User> users;
    public void addUser(User user) {
        users.add(user);
    }
}
