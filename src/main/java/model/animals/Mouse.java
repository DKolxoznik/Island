package main.java.model.animals;

import main.java.model.Herbivore;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Mouse extends Herbivore {
    public Mouse(Location location) {
        super(AnimalType.MOUSE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Mouse(location);
    }
}