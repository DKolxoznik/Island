package model;

import config.AnimalType;

public abstract class Predator extends Animal {
    public Predator(AnimalType type, Location location) {
        super(type, location);
    }

    @Override
    public boolean canEat(Plant plant) {
        return false;
    }

    @Override
    public boolean canEat(Animal animal) {
        // Хищники не едят других хищников (кроме особых случаев)
        if (animal instanceof Predator) {
            // Медведь может есть всех
            if (this.type == AnimalType.BEAR) return true;
            // Орел может есть лису
            if (this.type == AnimalType.EAGLE && animal.type == AnimalType.FOX) return true;
            // Удав может есть лису
            if (this.type == AnimalType.PYTHON && animal.type == AnimalType.FOX) return true;
            return false;
        }
        return true;
    }
}