package singleton.trainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainerServiceTest {

    @BeforeEach
    void clearRepository() {
        TrainerRepository.getInstance().clear();
    }

    @Test
    void createAndDoNotFind() {
        TrainerService.getInstance().createTrainer("John Doe");

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> TrainerService.getInstance().findByName("Jane"));
        assertEquals("Trainer not found with name: Jane", iae.getMessage());
    }

}