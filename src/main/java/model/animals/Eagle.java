package main.java.model.animals;

import main.java.model.Predator;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Eagle extends Predator {
    public Eagle(Location location) {
        super(AnimalType.EAGLE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Eagle(location);
    }
}