package training360.guinnessapp;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class RecorderNotFoundException extends AbstractThrowableProblem {

    public RecorderNotFoundException(Long id) {
        super(
                URI.create("/api/worldrecords"),
                "Recorder not found",
                Status.NOT_FOUND,
                String.format("Not found with id: %d", id)
        );
    }
}
