package authors;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping
    @Operation(summary = "Query all authors")
    public List<AuthorDTO> getAuthors() {
        return authorsService.getAuthors();
    }

    @PostMapping
    @Operation(summary = "Create an author")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO createAuthor(@RequestBody CreateAuthorCommand command) {
        return authorsService.createAuthor(command);
    }

    @PostMapping("{id}/books")
    @Operation(summary = "Add a book to author by id")
    public AuthorDTO addBookToAuthor(@PathVariable("id") long id, @RequestBody AddBookCommand command) {
        return authorsService.addBookToAuthor(id, command);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete an author by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthorById(@PathVariable("id") long id) {
        authorsService.deleteAuthorById(id);
    }


}
