package domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TrainSchedule {
    private String id;
    private Train train;
    private Route route;
    private LocalDateTime departureDate;
    private boolean isDelayed;
    private Map<String, Integer> bookings;

    public TrainSchedule(String id, Train train, Route route, LocalDateTime departureDate, boolean isDelayed) {
        this.id = id;
        this.train = train;
        this.route = route;
        this.departureDate = departureDate;
        this.isDelayed = isDelayed;
        this.bookings = new HashMap<>();
    }

    public int getTotalBookedSeats(){
        return bookings.values().stream().mapToInt(Integer::intValue).sum();
    }

    public boolean hasAvailableSeats(int requestedSeats){
        return (getTotalBookedSeats() + requestedSeats) <= train.getTotalCapacity();
    }

    public void addBooking (String customerEmail, int tickets){
        bookings.put(customerEmail,bookings.getOrDefault(customerEmail,0) + tickets);

    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    public void setDelayed(boolean delayed) {
        isDelayed = delayed;
    }

    public Map<String, Integer> getBookings() {
        return bookings;
    }

    public void setBookings(Map<String, Integer> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "TrainSchedule{" +
                "id='" + id + '\'' +
                ", train=" + train +
                ", route=" + route +
                ", departureDate=" + departureDate +
                ", isDelayed=" + isDelayed +
                ", bookings=" + bookings +
                '}';
    }
}
