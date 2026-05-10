import domain.Train;
import repository.TrainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainRepositoryTest {

    private TrainRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new TrainRepository();
    }

    @Test
    public void testAddAndFindTrain() {
        Train train = new Train("T-100", 50);
        repository.add(train);

        Train foundTrain = repository.findById("T-100");

        assertNotNull(foundTrain);
        assertEquals("T-100", foundTrain.getId());
        assertEquals(50, foundTrain.getTotalCapacity());
    }

    @Test
    public void testDuplicateIdThrowsException() {
        Train train1 = new Train("T-100", 50);
        Train train2 = new Train("T-100", 80); // Same ID!

        repository.add(train1);

        assertThrows(IllegalArgumentException.class, () -> {
            repository.add(train2);
        });
    }

    @Test
    public void testRemoveTrain() {
        Train train = new Train("T-100", 50);
        repository.add(train);

        repository.remove("T-100");

        // Should be null because we deleted it
        assertNull(repository.findById("T-100"));
        assertEquals(0, repository.findAll().size());
    }
}