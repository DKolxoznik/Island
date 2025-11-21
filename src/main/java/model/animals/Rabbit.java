package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Rabbit extends Herbivore {
    public Rabbit(Location location) {
        super(AnimalType.RABBIT, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Rabbit(location);
    }
}