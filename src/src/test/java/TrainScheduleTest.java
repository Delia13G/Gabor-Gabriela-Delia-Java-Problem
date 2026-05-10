import domain.Route;
import domain.RouteStop;
import domain.Train;
import domain.TrainSchedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TrainScheduleTest {

    private TrainSchedule schedule;

    @BeforeEach
    public void setUp() {
        // This method runs BEFORE every single test to give us fresh data
        Train train = new Train("TRN-1", 100); // Train has exactly 100 seats

        Route route = new Route("R-1");
        route.addStop(new RouteStop("A", LocalTime.of(8, 0), LocalTime.of(8, 10)));
        route.addStop(new RouteStop("B", LocalTime.of(9, 0), LocalTime.of(9, 10)));
        route.addStop(new RouteStop("C", LocalTime.of(10, 0), LocalTime.of(10, 10)));

        schedule = new TrainSchedule("SCH-1", train, route, LocalDateTime.now(), false);
    }

    @Test
    public void testBookingUnderCapacity() {
        // Arrange & Act
        schedule.addBooking("test@mail.com", 50, "A", "C");

        // Assert: There should be 50 seats left
        assertTrue(schedule.hasAvalableSeats("A", "C", 50), "Should allow exactly 50 more seats");
    }

    @Test
    public void testOverbookingIsPrevented() {
        // Arrange: Book 90 seats from A to C
        schedule.addBooking("first@mail.com", 90, "A", "C");

        // Act & Assert: Trying to book 20 more seats should fail because 90 + 20 > 100
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            schedule.addBooking("second@mail.com", 20, "A", "C");
        });

        assertEquals("Not enough seats", exception.getMessage());
    }

    @Test
    public void testDynamicCapacityBetweenStations() {
        // Arrange: 100 people travel from A to B. The train is completely full for this segment.
        schedule.addBooking("group1@mail.com", 100, "A", "B");

        // Assert:
        // 1. Trying to book from A to B should fail
        assertFalse(schedule.hasAvalableSeats("A", "B", 1));

        // 2. But booking from B to C should succeed, because all 100 people got off at B!
        assertTrue(schedule.hasAvalableSeats("B", "C", 100));
    }
}