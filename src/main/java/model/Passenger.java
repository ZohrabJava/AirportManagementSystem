package model;

public class Passenger {
    private String passengerName;
    private String passengerPhone;
    private int addressId;

    public Passenger(String passengerName, String passengerPhone) {
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
    }
    public Passenger(String passengerName, String passengerPhone, int addressId) {
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
        this.addressId = addressId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerName='" + passengerName + '\'' +
                ", passengerPhone='" + passengerPhone + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
