package model.animals;

import model.Predator;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Fox extends Predator {
    public Fox(Location location) {
        super(AnimalType.FOX, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Fox(location);
    }
}