package model;

import config.AnimalType;

public class Duck extends Herbivore {
    public Duck(Location location) {
        super(AnimalType.DUCK, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Duck(location);
    }
}