package com.example.learning.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//este util sa punem si proprietatea name, la @Entity, atunci cand vrem sa construim query-uri si in acele query-uri sa facem
//"referinta" catre acea tabela/entitate
@Getter
@Setter
@Entity(name = "user")
@Table(name = "user", schema = "public")
public class User {
    @Id
    @Column(name = "ID")
    //TODO: de cautat un exemplu cu diferentele de @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private Integer age;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER,
            orphanRemoval = false  //daca ar fi fost true-adica user ul nu mai are cartea ,o scot din lista ,este stearsa si din baza de date
            , mappedBy = "user") //cum se numeste entitatea(campul)User in clasa Copil
    //EAGER -DE FIECARE DATA ADUCE TOATE INFORMATIILE
    //LAZY -NU ADUCE TOATE GEN SI CARTILE CAND CERI USER UL
    //FOLOSIM MEREU LAZY DE LA PARINTE LA COPIL
    private List<Book> books = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_APPLICATION", schema = "public",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "application_id", nullable = false))
    private List<Application> applications = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            orphanRemoval = true
            , mappedBy = "user")
    private List<Car> cars = new ArrayList<>();

    public void addBook(Book book) {
        this.books.add(book);
        book.setUser(this);
    }

    public void addApplication(Application application) {
        this.applications.add(application);
        application.addUser(this);
    }

    public void addCar(Car car) {
        this.cars.add(car);
        car.setUser(this);
    }
}