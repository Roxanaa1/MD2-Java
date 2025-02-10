package com.example.learning.controller;

import com.example.learning.dto.BookDTO;
import com.example.learning.dto.UserDTO;
import com.example.learning.dto.mapper.BookMapper;
import com.example.learning.entities.Book;
import com.example.learning.entities.User;
import com.example.learning.service.BookService;
import com.example.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable Long userId, @RequestBody BookDTO bookDto) {
        Book bookToCreate = BookMapper.bookDto2Book(bookDto);
        Book createdBook = bookService.create(bookToCreate, userId);
        return ResponseEntity.ok(BookMapper.book2BookDto(createdBook));
    }
}
