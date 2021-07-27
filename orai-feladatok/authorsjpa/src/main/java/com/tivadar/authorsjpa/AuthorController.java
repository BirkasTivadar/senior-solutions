package com.tivadar.authorsjpa;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> listAuthors() {
        return authorService.listAuthors();
    }

    @PostMapping
    public AuthorDto createAuthor(@RequestBody CreateAuthorCommand command) {
        return authorService.createAuthor(command);
    }

    @PostMapping("/{id}")
    public AuthorDto addBookToAuthor(@PathVariable("id") long id, @RequestBody AddBookCommand command) {
        return authorService.addBookToAuthor(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable("id") long id){
        authorService.deleteAuthor(id);
    }
}



