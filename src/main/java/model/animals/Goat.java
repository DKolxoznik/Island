package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Goat extends Herbivore {
    public Goat(Location location) {
        super(AnimalType.GOAT, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Goat(location);
    }
}