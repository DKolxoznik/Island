package config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimulationConfigTest {

    @Test
    void testAnimalWeightsFromTable2() {
        // Проверяем веса животных из Таблицы 2
        assertEquals(50.0, SimulationConfig.ANIMAL_WEIGHTS.get(AnimalType.WOLF));
        assertEquals(15.0, SimulationConfig.ANIMAL_WEIGHTS.get(AnimalType.PYTHON));
        assertEquals(8.0, SimulationConfig.ANIMAL_WEIGHTS.get(AnimalType.FOX));
        assertEquals(2.0, SimulationConfig.ANIMAL_WEIGHTS.get(AnimalType.RABBIT));
        assertEquals(0.05, SimulationConfig.ANIMAL_WEIGHTS.get(AnimalType.MOUSE));
    }

    @Test
    void testMaxAnimalsPerCell() {
        // Максимальное количество на клетку из Таблицы 2
        assertEquals(30, SimulationConfig.MAX_ANIMALS_PER_CELL.get(AnimalType.WOLF));
        assertEquals(150, SimulationConfig.MAX_ANIMALS_PER_CELL.get(AnimalType.RABBIT));
        assertEquals(500, SimulationConfig.MAX_ANIMALS_PER_CELL.get(AnimalType.MOUSE));
        assertEquals(1000, SimulationConfig.MAX_ANIMALS_PER_CELL.get(AnimalType.CATERPILLAR));
    }

    @Test
    void testMovementSpeed() {
        // Скорость перемещения из Таблицы 2
        assertEquals(3, SimulationConfig.MOVEMENT_SPEED.get(AnimalType.WOLF));
        assertEquals(4, SimulationConfig.MOVEMENT_SPEED.get(AnimalType.HORSE));
        assertEquals(0, SimulationConfig.MOVEMENT_SPEED.get(AnimalType.CATERPILLAR));
    }

    @Test
    void testEatingProbabilitiesFromTable1() {
        // Вероятности поедания из Таблицы 1
        assertEquals(0.6, SimulationConfig.getEatingProbability(AnimalType.WOLF, AnimalType.RABBIT));
        assertEquals(0.7, SimulationConfig.getEatingProbability(AnimalType.FOX, AnimalType.RABBIT));
        assertEquals(0.9, SimulationConfig.getEatingProbability(AnimalType.FOX, AnimalType.MOUSE));
        assertEquals(0.4, SimulationConfig.getEatingProbability(AnimalType.FOX, AnimalType.CATERPILLAR));
        assertEquals(0.0, SimulationConfig.getEatingProbability(AnimalType.WOLF, AnimalType.BEAR));
    }

    @Test
    void testReproductionParameters() {
        // Проверяем параметры размножения
        assertEquals(0.05, SimulationConfig.REPRODUCTION_PROBABILITY.get(AnimalType.WOLF));
        assertEquals(0.12, SimulationConfig.REPRODUCTION_PROBABILITY.get(AnimalType.RABBIT));
        assertEquals(0.2, SimulationConfig.REPRODUCTION_PROBABILITY.get(AnimalType.CATERPILLAR));
    }

    @Test
    void testOffspringCount() {
        // Количество детенышей
        assertEquals(3, SimulationConfig.OFFSPRING_COUNT.get(AnimalType.WOLF));
        assertEquals(6, SimulationConfig.OFFSPRING_COUNT.get(AnimalType.RABBIT));
        assertEquals(20, SimulationConfig.OFFSPRING_COUNT.get(AnimalType.CATERPILLAR));
    }
}