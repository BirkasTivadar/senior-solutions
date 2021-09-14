package training360.guinnessapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "world_record")
public class WorldRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double value;

    private String unitOfMeasure;

    private LocalDate dateOfRecord;

    @OneToOne
    private Recorder recorder;

    public WorldRecord(String description, Double value, String unitOfMeasure, LocalDate dateOfRecord, Recorder recorder) {
        this.description = description;
        this.value = value;
        this.unitOfMeasure = unitOfMeasure;
        this.dateOfRecord = dateOfRecord;
        this.recorder = recorder;
    }

}
