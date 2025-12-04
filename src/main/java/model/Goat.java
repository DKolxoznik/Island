package model;

import config.AnimalType;

public class Goat extends Herbivore {
    public Goat(Location location) {
        super(AnimalType.GOAT, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Goat(location);
    }
}