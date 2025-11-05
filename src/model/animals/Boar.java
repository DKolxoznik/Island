package model.animals;

import model.Herbivore;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Boar extends Herbivore {
    public Boar(Location location) {
        super(AnimalType.BOAR, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Boar(location);
    }
}