package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Deer extends Herbivore {
    public Deer(Location location) {
        super(AnimalType.DEER, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Deer(location);
    }
}