import domain.Route;
import domain.Train;
import domain.TrainSchedule;
import repository.RouteRepository;
import repository.TrainRepository;
import repository.TrainScheduleRepository;
import service.TrainRouteService;
import service.TrainScheduleService;
import service.TrainService;
import ui.ConsoleUI;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args){
        TrainRepository trainRepo = new TrainRepository();
        RouteRepository routeRepo = new RouteRepository();
        TrainScheduleRepository scheduleRepo = new TrainScheduleRepository();

        TrainService trainService = new TrainService(trainRepo);
        TrainRouteService routeService = new TrainRouteService(routeRepo);
        TrainScheduleService scheduleService = new TrainScheduleService(scheduleRepo);

        seedData(trainRepo,routeRepo,scheduleRepo,routeService);

        ConsoleUI ui = new ConsoleUI(trainService,routeService,scheduleService);
        ui.start();
    }

    private static void seedData(TrainRepository trainRepo,RouteRepository routeRepo,
                                 TrainScheduleRepository scheduleRepo, TrainRouteService routeService){
        Train t1 = new Train("TRN-1", 100);
        Train t2 = new Train("TRN-2", 50);
        trainRepo.add(t1);
        trainRepo.add(t2);

        routeService.createRoute("R-NORTH");
        routeService.addStopToRoute("R-NORTH", "Cluj", LocalTime.of(8, 0), LocalTime.of(8, 15));
        routeService.addStopToRoute("R-NORTH", "Alba Iulia", LocalTime.of(10, 0), LocalTime.of(10, 15));
        routeService.addStopToRoute("R-NORTH", "Sibiu", LocalTime.of(12, 0), LocalTime.of(12, 15));

        routeService.createRoute("R-SOUTH");
        routeService.addStopToRoute("R-SOUTH", "Alba Iulia", LocalTime.of(11, 0), LocalTime.of(11, 30));
        routeService.addStopToRoute("R-SOUTH", "Deva", LocalTime.of(13, 0), LocalTime.of(13, 15));
        routeService.addStopToRoute("R-SOUTH", "Timisoara", LocalTime.of(15, 0), LocalTime.of(15, 15));

        Route rNorth = routeRepo.findById("R-NORTH");
        Route rSouth = routeRepo.findById("R-SOUTH");

        TrainSchedule sched1 = new TrainSchedule("SCH-1", t1, rNorth, LocalDateTime.now(), false);
        TrainSchedule sched2 = new TrainSchedule("SCH-2", t2, rSouth, LocalDateTime.now(), false);

        scheduleRepo.add(sched1);
        scheduleRepo.add(sched2);

        System.out.println("System Initialized with Test Data.");


    }
}
