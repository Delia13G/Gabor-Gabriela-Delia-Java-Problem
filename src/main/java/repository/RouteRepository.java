package repository;

import domain.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteRepository implements InterfaceRepository<Route,String> {
    private Map<String,Route> routes;

    public RouteRepository(){
        this.routes = new HashMap<>();
    }

    @Override
    public void add(Route entity){
        if(routes.containsKey(entity.getId())){
            throw new IllegalArgumentException(" A route with this ID already exists!");
        }
        routes.put(entity.getId(),entity);
    }

    @Override
    public Route findById(String id){
        return routes.get(id);
    }

    @Override
    public List<Route> findAll(){
        return new ArrayList<>(routes.values());
    }

    @Override
    public void update(Route entity){
        if(!routes.containsKey(entity.getId())){
            throw new IllegalArgumentException("Cannot update.Route ID not found");
        }
        routes.put(entity.getId(),entity);
    }

    @Override
    public void remove (String id){
        routes.remove(id);
    }


}
