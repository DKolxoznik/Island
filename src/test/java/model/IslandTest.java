package model;

import config.AnimalType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IslandTest {
    private Island island;

    @BeforeEach
    void setUp() {
        island = new Island(5, 5);
    }

    @Test
    void testIslandCreation() {
        assertNotNull(island);
        assertEquals(5, island.getWidth());
        assertEquals(5, island.getHeight());
        assertEquals(0, island.getTickCount());
    }

    @Test
    void testAddAnimal() {
        Location location = new Location(2, 2);
        Wolf wolf = new Wolf(location);

        island.addAnimal(wolf);

        Cell cell = island.getCell(location);
        assertTrue(cell.getAliveAnimals().contains(wolf));
    }

    @Test
    void testMoveAnimal() {
        Location startLocation = new Location(1, 1);
        Location endLocation = new Location(2, 2);
        Wolf wolf = new Wolf(startLocation);

        island.addAnimal(wolf);
        island.moveAnimal(wolf, startLocation, endLocation);

        Cell startCell = island.getCell(startLocation);
        Cell endCell = island.getCell(endLocation);

        assertFalse(startCell.getAliveAnimals().contains(wolf));
        assertTrue(endCell.getAliveAnimals().contains(wolf));
        assertEquals(endLocation, wolf.getLocation());
    }

    @Test
    void testIsValidLocation() {
        assertTrue(island.isValidLocation(0, 0));
        assertTrue(island.isValidLocation(4, 4));
        assertFalse(island.isValidLocation(-1, 0));
        assertFalse(island.isValidLocation(0, -1));
        assertFalse(island.isValidLocation(5, 0));
        assertFalse(island.isValidLocation(0, 5));
    }

    @Test
    void testProcessTick() {
        int initialTickCount = island.getTickCount();
        island.processTick();
        assertEquals(initialTickCount + 1, island.getTickCount());
    }

    @Test
    void testGetTotalAnimalCounts() {
        island.addAnimal(new Wolf(new Location(0, 0)));
        island.addAnimal(new Wolf(new Location(1, 1)));
        island.addAnimal(new Rabbit(new Location(2, 2)));
        island.addAnimal(new Rabbit(new Location(3, 3)));

        Map<AnimalType, Long> counts = island.getTotalAnimalCounts();

        assertEquals(2, counts.get(AnimalType.WOLF));
        assertEquals(2, counts.get(AnimalType.RABBIT));
        assertEquals(0, counts.get(AnimalType.BEAR));
    }

    @Test
    void testCleanupDeadAnimals() {
        Wolf liveWolf = new Wolf(new Location(0, 0));
        Wolf deadWolf = new Wolf(new Location(1, 1));
        deadWolf.die();

        island.addAnimal(liveWolf);
        island.addAnimal(deadWolf);

        island.cleanupDeadAnimals();

        Map<AnimalType, Long> counts = island.getTotalAnimalCounts();
        assertEquals(1, counts.get(AnimalType.WOLF));
    }

    @Test
    void testGetTotalPlantCount() {
        int totalPlants = island.getTotalPlantCount();
        assertTrue(totalPlants > 0);

        island.processTick();
        int newTotal = island.getTotalPlantCount();
        assertTrue(newTotal >= totalPlants);
    }
}