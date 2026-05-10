package ui;

import service.TrainRouteService;
import service.TrainScheduleService;
import service.TrainService;

import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleUI {
    private TrainService trainService;
    private TrainRouteService routeService;
    private TrainScheduleService scheduleService;
    private Scanner scanner;

    public ConsoleUI(TrainService trainService, TrainRouteService routeService, TrainScheduleService scheduleService) {
        this.trainService = trainService;
        this.routeService = routeService;
        this.scheduleService = scheduleService;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        while(true) {
            System.out.println("\n Train Ticketing System ");
            System.out.println("1. Customer Menu ");
            System.out.println("2. Administrator Menu ");
            System.out.println("0. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    customerMenu();
                    break;
                case "2":
                    adminMenu();
                    break;
                case "0":
                    System.out.println("Existing system.");
                    return;
                default:
                    System.out.println("Invalid option.Please try again");
            }
        }
    }

    private void customerMenu(){
        System.out.println("\n Customer Menu");
        System.out.println("1.Find direct connection");
        System.out.println("2.Find connection with transfers");
        System.out.println("3.Book a ticket");
        System.out.println("Choose: ");

        String choice = scanner.nextLine();
        if(choice.equals("1") || choice.equals("2")){
            System.out.println("Enter Departure Station: ");
            String start = scanner.nextLine();
            System.out.println("Enter Arrival Station: ");
            String end = scanner.nextLine();
            if(choice.equals("1")){
                System.out.println("\nSearching direct routes");
                scheduleService.findDirectConnection(start,end);
            } else {
                System.out.println("\nSearching transfer routes");
                scheduleService.findConnectionWithTransfers(start,end);
            }
        } else if ( choice.equals("3")){
            System.out.println("Enter schedule ID: ");
            String scheduleId = scanner.nextLine();
            System.out.println("Enter your Email: ");
            String email = scanner.nextLine();
            System.out.println("Enter start Station: ");
            String start = scanner.nextLine();
            System.out.println("Enter end Station");
            String end = scanner.nextLine();
            System.out.print("Number of Tickets: ");
            int tickets = Integer.parseInt(scanner.nextLine());
            scheduleService.bookTicket(scheduleId,email,tickets,start,end);
        }
    }
    private void adminMenu(){
        System.out.println("\nAdmin menu");
        System.out.println("       Train Management");
        System.out.println("1.View all trains");
        System.out.println("2.Add new train");
        System.out.println("3.Modify train capacity");
        System.out.println("4.Remove train");
        System.out.println("       Route Management");
        System.out.println("5.View all routes");
        System.out.println("6.Create new route");
        System.out.println("7.Add station(stop) to a route");
        System.out.println("8.Modify Route");
        System.out.println("9.Remove Route");
        System.out.println("       Operations");
        System.out.println("10.View bookings for a schedule");
        System.out.println("11.Mark train as delayed");
        System.out.println("Choose: ");

        String choice = scanner.nextLine();

        switch(choice){
            case "1":
                System.out.println(trainService.getAllTrains());
                break;
            case "2":
                System.out.println("Enter new train id: ");
                String id = scanner.nextLine();
                System.out.println("Enter total capacity: ");
                int capacity = Integer.parseInt(scanner.nextLine());
                trainService.addTrain(id,capacity);
                break;
            case "3":
                System.out.println("Enter train id to modify: ");
                String Id = scanner.nextLine();
                System.out.println("Enter new total capacity: ");
                int newCapacity = Integer.parseInt(scanner.nextLine());
                trainService.updateTrainCapacity(Id,newCapacity);
                break;
            case "4":
                System.out.println("Enter Train id to remove: ");
                String idToRemove = scanner.nextLine();
                trainService.removeTrain(idToRemove);
                break;
            case "5":
                System.out.println(routeService.getAllRoutes());
                break;
            case "6":
                System.out.println("Enter new route id: ");
                String routeId = scanner.nextLine();
                routeService.createRoute(routeId);
                break;
            case "7":
                System.out.println("Enter route id: ");
                String routeIdToAdd = scanner.nextLine();
                System.out.println("Enter station name: ");
                String station = scanner.nextLine();
                System.out.println("Enter arrival time (HH:MM) : ");
                LocalTime arrival = LocalTime.parse(scanner.nextLine());
                System.out.println("Enter departure time (HH:MM) :");
                LocalTime departure = LocalTime.parse(scanner.nextLine());
                routeService.addStopToRoute(routeIdToAdd,station,arrival,departure);
                break;
            case "8":
                System.out.println("Enter route id to modify: ");
                String routeIdToModify = scanner.nextLine();
                System.out.println("Enter 'a' for adding a new stop or 'd' to delete a stop:");
                String choiceForModify = scanner.nextLine();

                if (choiceForModify.equalsIgnoreCase("a")) {
                    System.out.println("Enter station name: ");
                    String stationToAdd = scanner.nextLine();
                    System.out.println("Enter arrival time (HH:MM): ");
                    LocalTime arrivalTime = LocalTime.parse(scanner.nextLine());
                    System.out.println("Enter departure time (HH:MM): ");
                    LocalTime departureTime = LocalTime.parse(scanner.nextLine());

                    routeService.addStopToRoute(routeIdToModify, stationToAdd, arrivalTime, departureTime);
                }else if (choiceForModify.equalsIgnoreCase("d")) {
                    System.out.print("Enter Station Name to remove from this route: ");
                    String stopToRemove = scanner.nextLine();

                    // Call the specific method to remove just the stop, not the whole route!
                    routeService.removeStopFromRoute(routeIdToModify, stopToRemove);
                } else {
                    System.out.println("Invalid choice. Returning to menu.");
                }
                break;
            case "9":
                System.out.println("Enter route id to remove: ");
                String routeIdToRemove = scanner.nextLine();
                routeService.removeRoute(routeIdToRemove);
                break;
            case "10":
                System.out.print("Enter Schedule ID to view bookings: ");
                String schedId = scanner.nextLine();
                domain.TrainSchedule sched = scheduleService.getSchedule(schedId);
                if (sched != null) {
                    System.out.println(sched.getBookings());
                } else {
                    System.out.println("Schedule not found.");
                }
                break;
            case "11":
                System.out.print("Enter Schedule ID to mark as delayed: ");
                String delayId = scanner.nextLine();
                scheduleService.marktrainDelayed(delayId);
                break;
            default:
                System.out.println("Invalid option.Please try again");




        }
    }
}
