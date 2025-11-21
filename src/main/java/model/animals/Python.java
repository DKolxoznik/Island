package main.java.model.animals;

import main.java.model.Predator;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Python extends Predator {
    public Python(Location location) {
        super(AnimalType.PYTHON, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Python(location);
    }
}