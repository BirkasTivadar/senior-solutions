package com.tivadar.authorsjpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String isbn, Author author) {
        this.isbn = isbn;
        this.author = author;
    }
}
