package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrainSchedule {
    private String id;
    private Train train;
    private Route route;
    private LocalDateTime departureDate;
    private boolean isDelayed;
    private List<Booking> bookings;

    public TrainSchedule(String id, Train train, Route route, LocalDateTime departureDate, boolean isDelayed) {
        this.id = id;
        this.train = train;
        this.route = route;
        this.departureDate = departureDate;
        this.isDelayed = isDelayed;
        this.bookings = new ArrayList<>();
    }

    private int getStationIndex(String stationName){
        List<RouteStop> stops = route.getStops();
        for(int i = 0; i < stops.size(); i++){
            if(stops.get(i).getStationName().equalsIgnoreCase(stationName)){
                return i;
            }
        }
        return -1;
    }



    public boolean hasAvalableSeats(String startStation, String endStation,int requestedSeats){
        List<RouteStop> stops = route.getStops();
        int startIndex = getStationIndex(startStation);
        int endIndex = getStationIndex(endStation);

        if(startIndex == -1 || endIndex == -1 || startIndex >= endIndex){
            return false;
        }

        for(int i = startIndex; i < endIndex; i++){
            int currentlyBookedForThisSegment = 0;
            for(Booking booking : bookings){
                int bookingStart = getStationIndex(booking.getStartStation());
                int bookingEnd = getStationIndex(booking.getEndStation());
                if(bookingStart <= i && bookingEnd > i){
                    currentlyBookedForThisSegment += booking.getNumberOfTickets();
                }
            }
            if(currentlyBookedForThisSegment + requestedSeats > train.getTotalCapacity()){
                return false;
            }
        }
        return true;
    }

    public void addBooking(String customerEmail, int tickets, String startStation,String endStation){
        if(hasAvalableSeats(startStation,endStation,tickets)){
            Booking newBooking = new Booking(customerEmail, tickets,startStation,endStation);
            bookings.add(newBooking);
        } else {
            throw new IllegalArgumentException("Not enough seats");
        }
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
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
