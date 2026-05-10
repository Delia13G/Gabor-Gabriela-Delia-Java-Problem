package service;

import domain.Train;
import repository.InterfaceRepository;

import javax.management.openmbean.OpenDataException;
import java.util.List;

public class TrainService {
    private InterfaceRepository<Train,String> trainRepository;

    public TrainService(InterfaceRepository<Train, String> trainRepository) {
        this.trainRepository = trainRepository;
    }

    public void addTrain(String id, int totalCapacity) {
        if(totalCapacity <= 0){
            throw new IllegalArgumentException("Train capacity must be grater than zero");
        }

        Train newTrain = new Train(id, totalCapacity);
        trainRepository.add(newTrain);
        System.out.println("Success: Train " + id + " has been added ");

    }

    public Train getTrain(String id){
         return trainRepository.findById(id);
    }

    public List<Train> getAllTrains(){
        return trainRepository.findAll();
    }

    public void updateTrainCapacity(String id, int newCapacity){
        if( newCapacity <=0){
            throw new IllegalArgumentException("Train capacity must be grater than zero");
        }

        Train train = trainRepository.findById(id);
        if(train == null){
            throw new IllegalArgumentException("Train not found");
        }

        train.setTotalCapacity(newCapacity);
        trainRepository.update(train);
        System.out.println("Success: Train " + id + " capacity updated to " + newCapacity);

    }

    public void removeTrain(String id){
        trainRepository.remove(id);
        System.out.println("Succes: Train " + id + " has been removed");
    }

}
