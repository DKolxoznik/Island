package model;

import config.AnimalType;

public class Deer extends Herbivore {
    public Deer(Location location) {
        super(AnimalType.DEER, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Deer(location);
    }
}