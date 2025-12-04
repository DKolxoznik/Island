package model.animals;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpecificAnimalTests {

    @Test
    void testBearIsOmnivorous() {
        Bear bear = new Bear(new Location(0, 0));
        Rabbit rabbit = new Rabbit(new Location(0, 0));
        Plant plant = new Plant();
        Wolf wolf = new Wolf(new Location(0, 0));

        assertTrue(bear.canEat(rabbit));
        assertTrue(bear.canEat(plant));
        assertTrue(bear.canEat(wolf)); // Медведь ест даже других хищников!
    }

    @Test
    void testDuckEatsCaterpillar() {
        Duck duck = new Duck(new Location(0, 0));
        Caterpillar caterpillar = new Caterpillar(new Location(0, 0));
        Plant plant = new Plant();

        assertTrue(duck.canEat(plant));
        assertTrue(duck.canEat(caterpillar));
    }

    @Test
    void testBoarEatsMouseAndCaterpillar() {
        Boar boar = new Boar(new Location(0, 0));
        Mouse mouse = new Mouse(new Location(0, 0));
        Caterpillar caterpillar = new Caterpillar(new Location(0, 0));
        Rabbit rabbit = new Rabbit(new Location(0, 0));

        assertTrue(boar.canEat(mouse));
        assertTrue(boar.canEat(caterpillar));
        assertFalse(boar.canEat(rabbit));
    }

    @Test
    void testCaterpillarDoesNotMove() {
        Caterpillar caterpillar = new Caterpillar(new Location(5, 5));
        Island island = new Island(10, 10);

        // Гусеница не должна двигаться
        Location newLocation = caterpillar.move(island, java.util.concurrent.ThreadLocalRandom.current());
        assertEquals(new Location(5, 5), newLocation);
    }

    @Test
    void testEagleCanEatFox() {
        Eagle eagle = new Eagle(new Location(0, 0));
        Fox fox = new Fox(new Location(0, 0));

        assertTrue(eagle.canEat(fox));
    }
}