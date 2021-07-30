package com.tivadar.authorsjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorsRepositoryIT {

    @Autowired
    AuthorsRepository repository;

    @Test
    void findAuthorByPrefixTest() {
        Author author = new Author("Tolkien");
        Author author1 = new Author("Solohov");
        Author author2 = new Author("Tolstoy");

        repository.save(author);
        repository.save(author1);
        repository.save(author2);

        List<Author> result = repository.findAuthorsByPrefix("Tol");

        assertThat(result).
                extracting(Author::getName)
                .containsExactly("Tolkien", "Tolstoy");
    }

@Test
    void generatedFindAuthorByPrefixTest() {
        Author author = new Author("Tolkien");
        Author author1 = new Author("Solohov");
        Author author2 = new Author("Tolstoy");

        repository.save(author);
        repository.save(author1);
        repository.save(author2);

        List<Author> result = repository.findAuthorByNameStartsWith("Tol");

        assertThat(result).
                extracting(Author::getName)
                .containsExactly("Tolkien", "Tolstoy");
    }


}