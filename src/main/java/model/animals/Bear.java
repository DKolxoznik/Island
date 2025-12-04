package model.animals;

import model.Plant;
import model.Predator;
import model.Animal;
import model.Location;
import config.AnimalType;

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