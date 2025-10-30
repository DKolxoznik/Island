package model.animals;

import model.Herbivore;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Sheep extends Herbivore {
    public Sheep(Location location) {
        super(AnimalType.SHEEP, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Sheep(location);
    }
}