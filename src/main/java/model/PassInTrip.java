package model;

import java.time.LocalDateTime;

public class PassInTrip {
    private int tripId;
    private int psgId;
    private LocalDateTime date;
    private String place;

    public PassInTrip(int tripId, int psgId,LocalDateTime date, String place) {
        this.tripId = tripId;
        this.psgId = psgId;
        this.date=date;
        this.place = place;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getPsgId() {
        return psgId;
    }

    public void setPsgId(int psgId) {
        this.psgId = psgId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PassInTrip{" +
                "tripId=" + tripId +
                ", psgId=" + psgId +
                ", date='" + date + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
