package cars;

import java.time.LocalDate;

public class KmState {

    private LocalDate localDate;

    private double km;

    public KmState(LocalDate localDate, double km) {
        this.localDate = localDate;
        this.km = km;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public double getKm() {
        return km;
    }

    @Override
    public String toString() {
        return "KmState{" +
                "localDate=" + localDate +
                ", km=" + km +
                '}';
    }
}
