package model.animals;

import model.Predator;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Eagle extends Predator {
    public Eagle(Location location) {
        super(AnimalType.EAGLE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Eagle(location);
    }
}