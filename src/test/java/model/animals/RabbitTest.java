package model.animals;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RabbitTest {

    @Test
    void testRabbitIsHerbivore() {
        Rabbit rabbit = new Rabbit(new Location(0, 0));
        Plant plant = new Plant();
        Wolf wolf = new Wolf(new Location(0, 0));

        assertTrue(rabbit.canEat(plant));
        assertFalse(rabbit.canEat(wolf));
    }

    @Test
    void testRabbitWeight() {
        Rabbit rabbit = new Rabbit(new Location(0, 0));
        assertEquals(2.0, rabbit.getWeight(), 0.01);
    }

    @Test
    void testRabbitMovement() {
        Rabbit rabbit = new Rabbit(new Location(5, 5));
        Island island = new Island(10, 10);

        // Кролик может двигаться
        Location newLocation = rabbit.move(island, java.util.concurrent.ThreadLocalRandom.current());
        assertNotNull(newLocation);
    }
}