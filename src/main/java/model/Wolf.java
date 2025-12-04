package model;

import config.AnimalType;

public class Wolf extends Predator {
    public Wolf(Location location) {
        super(AnimalType.WOLF, location);
    }

    @Override
    public Animal createOffspring(Location location) {
        return new Wolf(location);
    }
}