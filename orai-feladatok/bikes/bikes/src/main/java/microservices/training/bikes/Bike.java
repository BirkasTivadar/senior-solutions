package microservices.training.bikes;

import java.time.LocalDateTime;

public class Bike {

    private String bikeId;

    private String LastUserId;

    private LocalDateTime lastReturnTime;

    private double lastDistance;

    public Bike(String bikeId, String lastUserId, LocalDateTime lastReturnTime, double lastDistance) {
        this.bikeId = bikeId;
        LastUserId = lastUserId;
        this.lastReturnTime = lastReturnTime;
        this.lastDistance = lastDistance;
    }

    public String getBikeId() {
        return bikeId;
    }

    public String getLastUserId() {
        return LastUserId;
    }

    public LocalDateTime getLastReturnTime() {
        return lastReturnTime;
    }

    public double getLastDistance() {
        return lastDistance;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "bikeId='" + bikeId + '\'' +
                ", LastUserId='" + LastUserId + '\'' +
                ", lastReturnTime=" + lastReturnTime +
                ", lastDistance=" + lastDistance +
                '}';
    }
}
