package service;

import domain.Route;
import domain.RouteStop;
import repository.InterfaceRepository;

import java.time.LocalTime;
import java.util.List;

public class TrainRouteService {
    private InterfaceRepository<Route,String> routeRepository;

    public TrainRouteService(InterfaceRepository<Route, String> routeRepository) {
        this.routeRepository = routeRepository;
    }

    public void createRoute(String id){
        Route newRoute = new Route(id);
        routeRepository.add(newRoute);
        System.out.println("Success: route " + id +  " created ");
    }

    public void addStopToRoute(String routeId, String stationName, LocalTime arrival, LocalTime departure){
           Route route = routeRepository.findById(routeId);

           if(route == null){
               throw new IllegalArgumentException("Route not found");
           }

           RouteStop newStop = new RouteStop(stationName, arrival,departure);
           route.addStop(newStop);

           routeRepository.update(route);
           System.out.println("Success: Added stop " + stationName + " to route " +  routeId);
    }
    public Route getRoute(String id){
        return routeRepository.findById(id);
    }

    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }

    public void removeRoute(String id) {
        routeRepository.remove(id);
        System.out.println("Success: Route " + id + "has been removed ");
    }

    

}
