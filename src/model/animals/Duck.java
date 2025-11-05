package model.animals;

import model.Herbivore;
import model.Animal;
import model.Location;
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