package com.tivadar.authorsjpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookCommand {

    private String isbn;

    private Author author;


}
