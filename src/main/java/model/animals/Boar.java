package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Boar extends Herbivore {
    public Boar(Location location) {
        super(AnimalType.BOAR, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Boar(location);
    }
}