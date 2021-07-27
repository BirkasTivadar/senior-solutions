package com.tivadar.authorsjpa;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private ModelMapper modelMapper;

    private AuthorsRepository repository;

    public List<AuthorDto> listAuthors() {
        return repository.findAll().stream()
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .toList();
    }

    public AuthorDto createAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        repository.save(author);
        return modelMapper.map(author, AuthorDto.class);
    }

    @Transactional
    public AuthorDto addBookToAuthor(long id, AddBookCommand command) {
        Author author = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found author this id"));
        Book book = new Book(command.getIsbn(), command.getTitle());
        author.addBook(book);
        return modelMapper.map(author, AuthorDto.class);
    }

    public void deleteAuthor(long id) {
        repository.deleteById(id);
    }
}
