package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Duck extends Herbivore {
    public Duck(Location location) {
        super(AnimalType.DUCK, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Duck(location);
    }
}