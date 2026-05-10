package domain;

public class Booking {
    private String customerEmail;
    private int numberOfTickets;
    private String startStation;
    private String endStation;

    public Booking(String customerEmail, int numberOfTickets, String startStation, String endStation) {
        this.customerEmail = customerEmail;
        this.numberOfTickets = numberOfTickets;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customerEmail='" + customerEmail + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                '}';
    }
}
