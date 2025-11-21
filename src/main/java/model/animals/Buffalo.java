package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Buffalo extends Herbivore {
    public Buffalo(Location location) {
        super(AnimalType.BUFFALO, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Buffalo(location);
    }
}