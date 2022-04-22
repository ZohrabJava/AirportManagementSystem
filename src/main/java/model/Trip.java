package model;

import java.time.LocalDateTime;

public class Trip {
    private int tripNumber;
    private int  companyId;
    private String plane;
    private String townFrom;
    private  String townTo;
    private LocalDateTime timeOut;
    private LocalDateTime timeIn;

    public Trip(int tripNumber, int companyId,String plane, String townFrom,
                String townTo, LocalDateTime tomiOut, LocalDateTime timeIn) {
        this.tripNumber = tripNumber;
        this.companyId = companyId;
        this.plane=plane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = tomiOut;
        this.timeIn = timeIn;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripNumber=" + tripNumber +
                ", companyId=" + companyId +
                ", plane='" + plane + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", tomiOut=" + timeOut +
                ", timeIn=" + timeIn +
                '}';
    }
}
