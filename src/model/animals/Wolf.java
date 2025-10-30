package model.animals;

import model.Predator;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Wolf extends Predator {
    public Wolf(Location location) {
        super(AnimalType.WOLF, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Wolf(location);
    }
}