package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Sheep extends Herbivore {
    public Sheep(Location location) {
        super(AnimalType.SHEEP, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Sheep(location);
    }
}