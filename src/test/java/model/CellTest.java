package model;

import config.AnimalType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private Cell cell;
    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(3, 3);
        cell = new Cell(location);
    }

    @Test
    void testCellInitialization() {
        assertEquals(location, cell.getLocation());
        assertTrue(cell.getPlantCount() > 0);
        assertEquals(0, cell.getAliveAnimals().size());
    }

    @Test
    void testAddAnimalWithinCapacity() {
        Rabbit rabbit = new Rabbit(location);
        assertTrue(cell.canAddAnimal(AnimalType.RABBIT));

        cell.addAnimal(rabbit);
        assertEquals(1, cell.getAliveAnimals().size());
        assertTrue(cell.getAliveAnimals().contains(rabbit));
    }

    @Test
    void testCannotExceedMaxCapacity() {
        // Попробуем добавить больше волков, чем разрешено
        int maxWolves = config.SimulationConfig.MAX_ANIMALS_PER_CELL.get(AnimalType.WOLF);

        for (int i = 0; i < maxWolves + 5; i++) {
            Wolf wolf = new Wolf(location);
            cell.addAnimal(wolf);
        }

        // Проверяем что не превысили лимит
        long wolfCount = cell.getAnimalCounts().getOrDefault(AnimalType.WOLF, 0L);
        assertTrue(wolfCount <= maxWolves);
    }

    @Test
    void testRemoveDeadAnimals() {
        Rabbit aliveRabbit = new Rabbit(location);
        Rabbit deadRabbit = new Rabbit(location);
        deadRabbit.die();

        cell.addAnimal(aliveRabbit);
        cell.addAnimal(deadRabbit);

        assertEquals(2, cell.getAnimals().size());
        cell.removeDeadAnimals();
        assertEquals(1, cell.getAliveAnimals().size());
        assertTrue(cell.getAliveAnimals().contains(aliveRabbit));
    }

    @Test
    void testPlantGrowth() {
        int initialCount = cell.getPlantCount();

        // Вызываем рост растений много раз
        for (int i = 0; i < 100; i++) {
            cell.growPlants();
        }

        int finalCount = cell.getPlantCount();
        assertTrue(finalCount >= initialCount);
        assertTrue(finalCount <= config.SimulationConfig.MAX_PLANTS_PER_CELL);
    }
}