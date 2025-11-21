package main.java.model.animals;

import main.java.model.Predator;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Wolf extends Predator {
    public Wolf(Location location) {
        super(AnimalType.WOLF, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Wolf(location);
    }
}