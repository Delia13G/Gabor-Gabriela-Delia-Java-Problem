package repository;

import domain.Train;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainRepository implements InterfaceRepository<Train,String>{
    private Map<String, Train> trains;

    public TrainRepository(){
        this.trains = new HashMap<>();
    }

    @Override
    public void add(Train entity){
        if(trains.containsKey(entity.getId())){
            throw new IllegalArgumentException(" A train with this ID already exists");
        }
        trains.put(entity.getId(),entity);
    }

    @Override
    public Train findById(String id){
        return trains.get(id);
    }

    @Override
    public List<Train> findAll(){
        return new ArrayList<>(trains.values());
    }

    @Override
    public void update(Train entity){
        if(!trains.containsKey(entity.getId())){
            throw new IllegalArgumentException("Cannot update.Train ID not found");
        }
        trains.put(entity.getId(),entity);
    }

    @Override
    public void remove(String id){
        trains.remove(id);
    }

}
