package model;

import config.AnimalType;

public abstract class Herbivore extends Animal {
    public Herbivore(AnimalType type, Location location) {
        super(type, location);
    }

    @Override
    public boolean canEat(Plant plant) {
        return true;
    }

    @Override
    public boolean canEat(Animal animal) {
        // Некоторые травоядные могут есть других животных
        switch (this.type) {
            case DUCK: return animal.type == AnimalType.CATERPILLAR;
            case MOUSE: return animal.type == AnimalType.CATERPILLAR;
            case BOAR: return animal.type == AnimalType.MOUSE || animal.type == AnimalType.CATERPILLAR;
            default: return false;
        }
    }
}