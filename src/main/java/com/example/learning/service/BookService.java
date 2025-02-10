package com.example.learning.service;

import com.example.learning.entities.Book;
import com.example.learning.entities.User;
import com.example.learning.repository.BookRepository;
import com.example.learning.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public Book create(Book bookToCreate, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        //varianta comentata ar fi fost fara metoda addBook() in User, unde am setat
        //relatia bidirectional
        //user.getBooks().add(bookToCreate);
        //bookToCreate.setUser(user);
        user.addBook(bookToCreate);
        bookRepository.save(bookToCreate);
        return bookToCreate;
    }
}
