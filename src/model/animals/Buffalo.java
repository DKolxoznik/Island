package model.animals;

import model.Herbivore;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Buffalo extends Herbivore {
    public Buffalo(Location location) {
        super(AnimalType.BUFFALO, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Buffalo(location);
    }
}