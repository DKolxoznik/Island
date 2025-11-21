package main.java.model.animals;

import main.java.model.Plant;
import main.java.model.Predator;
import main.java.model.Animal;
import main.java.model.Location;
import main.java.config.AnimalType;

public class Bear extends Predator {
    public Bear(Location location) {
        super(AnimalType.BEAR, location);
    }

    @Override
    public boolean canEat(Plant plant) {
        return true; // Медведь всеядный
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Bear(location);
    }
}