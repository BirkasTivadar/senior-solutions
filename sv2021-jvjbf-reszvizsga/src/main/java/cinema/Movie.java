package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;

    private String title;

    private LocalDateTime date;

    private Integer maxSpaces;

    private Integer freeSpaces;

    public Movie(Long id, String title, LocalDateTime date, Integer maxSpaces) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.maxSpaces = maxSpaces;
        this.freeSpaces = maxSpaces;
    }

    public void reservation(Integer spaces) {
        if (freeSpaces > spaces) {
            freeSpaces -= spaces;
        } else {
            throw new IllegalStateException("Not enough free spaces");
        }
    }
}
