package model;

import config.AnimalType;

public class Python extends Predator {
    public Python(Location location) {
        super(AnimalType.PYTHON, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Python(location);
    }
}