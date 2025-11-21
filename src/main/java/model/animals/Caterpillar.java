package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Island;
import main.java.model.Location;
import main.java.config.AnimalType;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Herbivore {
    public Caterpillar(Location location) {
        super(AnimalType.CATERPILLAR, location);
    }

    @Override
    public Location move(Island island, ThreadLocalRandom random) {
        // Гусеницы не двигаются
        return location;
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Caterpillar(location);
    }
}