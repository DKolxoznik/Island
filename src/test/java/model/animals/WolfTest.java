package model.animals;

import model.*;
import config.AnimalType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WolfTest {

    @Test
    void testWolfIsPredator() {
        Wolf wolf = new Wolf(new Location(0, 0));
        Rabbit rabbit = new Rabbit(new Location(0, 0));
        Plant plant = new Plant();

        assertTrue(wolf.canEat(rabbit));
        assertFalse(wolf.canEat(plant));
    }

    @Test
    void testWolfCannotEatOtherPredators() {
        Wolf wolf1 = new Wolf(new Location(0, 0));
        Wolf wolf2 = new Wolf(new Location(0, 0));
        Bear bear = new Bear(new Location(0, 0));

        assertFalse(wolf1.canEat(wolf2));
        assertFalse(wolf1.canEat(bear));
    }

    @Test
    void testWolfReproduction() {
        Wolf wolf = new Wolf(new Location(0, 0));
        Wolf offspring = (Wolf) wolf.createOffspring(new Location(1, 1));

        assertNotNull(offspring);
        assertEquals(AnimalType.WOLF, offspring.getType());
        assertNotSame(wolf, offspring);
    }
}