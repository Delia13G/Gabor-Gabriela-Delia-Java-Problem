package service;

import domain.RouteStop;
import domain.TrainSchedule;
import repository.InterfaceRepository;

import java.util.ArrayList;
import java.util.List;

public class TrainScheduleService {
    private InterfaceRepository<TrainSchedule,String> scheduleRepository;

    public TrainScheduleService(InterfaceRepository<TrainSchedule, String> scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    private int getStationIndex(TrainSchedule schedule, String stationName){
        List<RouteStop> stops = schedule.getRoute().getStops();
        for(int i = 0;i < stops.size(); i++){
            if(stops.get(i).getStationName().equalsIgnoreCase(stationName)){
                return i;
            }
        }
        return -1;
    }

    public List<TrainSchedule> findDirectConnection(String startStation, String endStation){
        List<TrainSchedule> directSchedule = new ArrayList<>();
        List<TrainSchedule> allSchedule = scheduleRepository.findAll();

        for(TrainSchedule schedule : allSchedule) {
            int startIndex = getStationIndex(schedule, startStation);
            int endIndex = getStationIndex(schedule, endStation);

            if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                directSchedule.add(schedule);
            }
        }
            if(directSchedule.isEmpty()){
                System.out.println("Error. No direct link found between" + startStation + " and " + endStation);

            }
        return directSchedule;
    }

    public void findConnectionWithTransfers(String startStation,String endStation){
        List<TrainSchedule> allSchedules = scheduleRepository.findAll();
        boolean connectionFound = false;

        for(TrainSchedule firstLink: allSchedules){
            int startIndex = getStationIndex(firstLink,startStation);
            if(startIndex == -1 ) continue;

            List<RouteStop> firstStops = firstLink.getRoute().getStops();
            for(int i = startIndex + 1 ; i < firstStops.size(); i++){
                String transferStation = firstStops.get(i).getStationName();
                for(TrainSchedule secondLink : allSchedules){
                    if(firstLink.getId().equals(secondLink.getId())) continue;

                    int transferIndexOnSecondLink = getStationIndex(secondLink,transferStation);
                    int endIndex = getStationIndex(secondLink,endStation);

                    if(transferIndexOnSecondLink != -1 && endIndex != -1 && transferIndexOnSecondLink < endIndex){

                        System.out.println("Found Transfer Route");
                        System.out.println(" 1.Take " + firstLink.getTrain().getId() + "from " + startStation + " to " + transferStation);
                        System.out.println(" 2. Change at " + transferStation);
                        System.out.println(" 3.Take " + secondLink.getTrain().getId() + " to " + endStation);
                        connectionFound = true;
                    }
                }

            }
        }
        if(!connectionFound){
            System.out.println("No transfer connections found");
        }
    }

    public void bookTicket(String scheduleId, String customerEmail,int tickets,String  startStation, String endStation) {
        TrainSchedule schedule = scheduleRepository.findById(scheduleId);

        if (schedule == null) {
            throw new IllegalArgumentException("Schedule not found");
        }

        try {
            schedule.addBooking(customerEmail, tickets, startStation, endStation);
            scheduleRepository.update(schedule);
            sendEmail(customerEmail, "Booking Confirmation", "You have successfully booked " + tickets + " tickets from " + startStation + " to " + endStation);

        } catch (IllegalArgumentException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }

    private void sendEmail(String toAddress, String subject, String body){
        System.out.println("      Mock Email       ");
        System.out.println("To: " + toAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message" + body);
    }

    public void marktrainDelayed(String scheduleId){
        TrainSchedule schedule = scheduleRepository.findById(scheduleId);

        if(schedule == null){
            throw new IllegalArgumentException("Schedule not found");
        }

        schedule.setDelayed(true);
        scheduleRepository.update(schedule);

        for(domain.Booking booking: schedule.getBookings()){
              sendEmail(booking.getCustomerEmail(), "Train Delayed", "We are sorrt to inform you that your train from"
                      + booking.getStartStation() + " to " + booking.getEndStation() + " has been delyed");
        }
    }


    public TrainSchedule getSchedule(String schedId) {
        return scheduleRepository.findById(schedId);
    }
}
