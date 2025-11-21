package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Horse extends Herbivore {
    public Horse(Location location) {
        super(AnimalType.HORSE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Horse(location);
    }
}