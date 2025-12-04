package model;

import config.AnimalType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(5, 5);
    }

    @Test
    void testAnimalCreationAndInitialState() {
        Wolf wolf = new Wolf(location);

        assertNotNull(wolf);
        assertEquals(AnimalType.WOLF, wolf.getType());
        assertEquals(location, wolf.getLocation());
        assertTrue(wolf.isAlive());
        assertTrue(wolf.getCurrentSatiety() > 0);
        assertTrue(wolf.getSatietyPercent() > 0 && wolf.getSatietyPercent() <= 1.0);
    }

    @Test
    void testEatPlantSuccess() {
        Deer deer = new Deer(location);
        Plant plant = new Plant();
        double initialSatiety = deer.getCurrentSatiety();

        assertTrue(plant.isAlive());
        deer.eat(plant, ThreadLocalRandom.current());

        assertTrue(deer.getCurrentSatiety() > initialSatiety);
        assertFalse(plant.isAlive());
    }

    @Test
    void testDeathFromStarvation() {
        Rabbit rabbit = new Rabbit(location);

        // Симулируем голод - многократно уменьшаем сытость
        for (int i = 0; i < 15; i++) {
            rabbit.decreaseSatiety();
        }

        assertFalse(rabbit.isAlive());
    }

    @Test
    void testMoveReturnsValidLocation() {
        Island island = new Island(10, 10);
        Wolf wolf = new Wolf(location);

        Location newLocation = wolf.move(island, ThreadLocalRandom.current());

        assertNotNull(newLocation);
        assertTrue(newLocation.getX() >= 0 && newLocation.getX() < 10);
        assertTrue(newLocation.getY() >= 0 && newLocation.getY() < 10);
    }
}