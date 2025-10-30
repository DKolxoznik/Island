package model.animals;

import model.Herbivore;
import model.Animal;
import model.Island;
import model.Location;
import config.AnimalType;
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