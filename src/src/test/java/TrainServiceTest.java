import domain.Train;
import repository.TrainRepository;
import service.TrainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainServiceTest {

    private TrainRepository repository;
    private TrainService service;

    @BeforeEach
    public void setUp() {
        repository = new TrainRepository();
        service = new TrainService(repository);
    }

    @Test
    public void testAddValidTrain() {
        service.addTrain("TRN-99", 200);

        Train saved = repository.findById("TRN-99");
        assertNotNull(saved);
        assertEquals(200, saved.getTotalCapacity());
    }

    @Test
    public void testAddTrainWithNegativeCapacityFails() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addTrain("TRN-BAD", -10);
        });

        assertEquals("Train capacity must be grater than zero", exception.getMessage());
    }
}