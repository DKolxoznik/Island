package model.animals;

import model.Herbivore;
import model.Animal;
import model.Location;
import config.AnimalType;

public class Mouse extends Herbivore {
    public Mouse(Location location) {
        super(AnimalType.MOUSE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Mouse(location);
    }
}