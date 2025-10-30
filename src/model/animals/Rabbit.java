package model.animals;

import model.Herbivore;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Rabbit extends Herbivore {
    public Rabbit(Location location) {
        super(AnimalType.RABBIT, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Rabbit(location);
    }
}