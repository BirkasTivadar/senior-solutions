package authors;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorsService {

    private ModelMapper modelMapper;

    private AuthorsRepository repository;

    public List<AuthorDTO> getAuthors() {
        return repository.findAll().stream()
                .map(auhtor -> modelMapper.map(auhtor, AuthorDTO.class))
                .toList();
    }

    public AuthorDTO createAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        repository.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Transactional
    public AuthorDTO addBookToAuthor(long id, AddBookCommand command) {
        Author author = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("author not found: " + id));
        Book book = new Book(command.getIsbn(), command.getTitle());
        author.addBook(book);
        return modelMapper.map(author, AuthorDTO.class);
    }

    public void deleteAuthorById(long id) {
        repository.deleteById(id);
    }
}
