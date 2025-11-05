package model;

import config.SimulationConfig;
import config.AnimalType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    protected AnimalType type;
    protected double weight;
    protected double maxWeight;
    protected double maxFoodRequired;
    protected int maxSpeed;
    protected double currentSatiety;
    protected Location location;
    protected boolean isAlive;

    public Animal(AnimalType type, Location location) {
        this.type = type;
        this.weight = SimulationConfig.ANIMAL_WEIGHTS.get(type);
        this.maxWeight = this.weight * 1.5;
        this.maxFoodRequired = SimulationConfig.MAX_FOOD_REQUIRED.get(type);
        this.maxSpeed = SimulationConfig.MOVEMENT_SPEED.get(type);
        this.location = location;
        this.currentSatiety = maxFoodRequired / 2.0;
        this.isAlive = true;
    }

    // Абстрактные методы
    public abstract boolean canEat(Animal animal);
    public abstract boolean canEat(Plant plant);

    // Общие методы
    public void eat(Object food, ThreadLocalRandom random) {
        if (!isAlive) return;

        if (food instanceof Animal) {
            Animal prey = (Animal) food;
            if (canEat(prey) && prey.isAlive()) {
                double probability = SimulationConfig.getEatingProbability(this.type, prey.type);
                if (random.nextDouble() < probability) {
                    currentSatiety += prey.weight;
                    currentSatiety = Math.min(currentSatiety, maxFoodRequired);
                    prey.die();
                }
            }
        } else if (food instanceof Plant) {
            Plant plant = (Plant) food;
            if (canEat(plant) && plant.isAlive()) {
                currentSatiety += plant.getNutritionalValue();
                currentSatiety = Math.min(currentSatiety, maxFoodRequired);
                plant.consume();
            }
        }
    }

    public Location move(Island island, ThreadLocalRandom random) {
        if (!isAlive || maxSpeed == 0) return location;

        List<Location> possibleMoves = getPossibleMoves(island);
        if (possibleMoves.isEmpty()) return location;

        Location newLocation = possibleMoves.get(random.nextInt(possibleMoves.size()));

        // Уменьшаем сытость при движении
        currentSatiety -= maxFoodRequired * 0.05;
        if (currentSatiety < 0) currentSatiety = 0;

        return newLocation;
    }

    protected List<Location> getPossibleMoves(Island island) {
        List<Location> moves = new ArrayList<>();
        int speed = ThreadLocalRandom.current().nextInt(maxSpeed + 1);

        for (int dx = -speed; dx <= speed; dx++) {
            for (int dy = -speed; dy <= speed; dy++) {
                if (dx == 0 && dy == 0) continue;
                if (Math.abs(dx) + Math.abs(dy) > speed) continue;

                int newX = location.getX() + dx;
                int newY = location.getY() + dy;

                if (island.isValidLocation(newX, newY)) {
                    moves.add(new Location(newX, newY));
                }
            }
        }
        return moves;
    }

    public boolean tryReproduce(Island island, ThreadLocalRandom random) {
        if (!isAlive || random.nextDouble() >= SimulationConfig.REPRODUCTION_PROBABILITY.get(type)) {
            return false;
        }

        Cell currentCell = island.getCell(location);
        long sameTypeCount = currentCell.getAnimals().stream()
                .filter(a -> a.type == this.type && a.isAlive)
                .count();

        if (sameTypeCount >= 2) {
            int offspringCount = SimulationConfig.OFFSPRING_COUNT.get(type);
            for (int i = 0; i < offspringCount; i++) {
                island.addAnimal(createOffspring(location));
            }
            return true;
        }
        return false;
    }

    protected abstract Animal createOffspring(Location location);

    public void decreaseSatiety() {
        if (!isAlive) return;

        currentSatiety -= maxFoodRequired * 0.1;
        if (currentSatiety <= 0) {
            die();
        }
    }

    public void die() {
        isAlive = false;
    }

    // Геттеры
    public AnimalType getType() { return type; }
    public double getWeight() { return weight; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public boolean isAlive() { return isAlive; }
    public double getCurrentSatiety() { return currentSatiety; }
    public double getSatietyPercent() {
        return maxFoodRequired > 0 ? currentSatiety / maxFoodRequired : 1.0;
    }
}