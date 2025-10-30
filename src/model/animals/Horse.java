package model.animals;

import model.Herbivore;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Horse extends Herbivore {
    public Horse(Location location) {
        super(AnimalType.HORSE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Horse(location);
    }
}