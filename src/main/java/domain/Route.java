package domain;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private String id;
    private List<RouteStop> stops;

    public Route(String id){
        this.id = id;
        this.stops = new ArrayList<>();
    }

    public void addStop(RouteStop stop){
        this.stops.add(stop);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RouteStop> getStops() {
        return stops;
    }

    public void setStops(List<RouteStop> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", stops=" + stops +
                '}';
    }
}
