package repository;

import domain.TrainSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainScheduleRepository implements InterfaceRepository<TrainSchedule,String> {
    private Map<String,TrainSchedule> schedules;

    public TrainScheduleRepository(){
        this.schedules = new HashMap<>();
    }

    @Override
    public void add(TrainSchedule entity){
        if(schedules.containsKey(entity.getId())){
            throw new IllegalArgumentException("A train schedule with this ID already exists");
        }
        schedules.put(entity.getId(), entity);
    }

    @Override
    public TrainSchedule findById(String id){
        return schedules.get(id);
    }

    @Override
    public List<TrainSchedule> findAll(){
        return new ArrayList<>(schedules.values());
    }

    @Override
    public void update(TrainSchedule entity){
        if(!schedules.containsKey(entity.getId())){
            throw new IllegalArgumentException("Cannot update: Train schedule ID not found");
        }
        schedules.put(entity.getId(),entity);
    }

    @Override
    public void remove(String id){
        schedules.remove(id);
    }

}
