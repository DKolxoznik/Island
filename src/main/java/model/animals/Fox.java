package main.java.model.animals;

import main.java.model.Predator;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Fox extends Predator {
    public Fox(Location location) {
        super(AnimalType.FOX, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Fox(location);
    }
}