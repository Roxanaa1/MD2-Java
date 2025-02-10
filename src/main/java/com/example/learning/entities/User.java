package com.example.learning.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//este util sa punem si proprietatea name, la @Entity, atunci cand vrem sa construim query-uri si in acele query-uri sa facem
//"referinta" catre acea tabela/entitate
@Entity(name = "user")
@Table(name="user", schema = "public")
public class User {
    @Id
    @Column(name="ID")
    //TODO: de cautat un exemplu cu diferentele de @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="AGE")
    private Integer age;
    @OneToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},
            fetch = FetchType.EAGER,
            orphanRemoval = false  //daca ar fi fost true-adica user ul nu mai are cartea ,o scot din lista ,este stearsa si din baza de date
                    ,mappedBy = "user") //cum se numeste entitatea(campul)User in clasa Copil
    //EAGER -DE FIECARE DATA ADUCE TOATE INFORMATIILE
    //LAZY -NU ADUCE TOATE GEN SI CARTILE CAND CERI USER UL
    //FOLOSIM MEREU LAZY DE LA PARINTE LA COPIL
    private List<Book> books=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}