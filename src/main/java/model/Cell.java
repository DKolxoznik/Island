package model;

import config.SimulationConfig;
import config.AnimalType;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Cell {
    private final Location location;
    private final List<Animal> animals;
    private final List<Plant> plants;
    private final AtomicInteger plantCount;

    public Cell(Location location) {
        this.location = location;
        this.animals = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
        this.plantCount = new AtomicInteger(0);
        initializePlants();
    }

    private void initializePlants() {
        int initialPlants = ThreadLocalRandom.current().nextInt(
                SimulationConfig.INITIAL_PLANTS_MIN,
                SimulationConfig.INITIAL_PLANTS_MAX
        );
        for (int i = 0; i < initialPlants; i++) {
            plants.add(new Plant());
        }
        plantCount.set(plants.size());
    }

    public void addAnimal(Animal animal) {
        if (canAddAnimal(animal.getType())) {
            animals.add(animal);
        }
    }

    public boolean canAddAnimal(AnimalType type) {
        long count = animals.stream()
                .filter(a -> a.getType() == type && a.isAlive())
                .count();
        return count < SimulationConfig.MAX_ANIMALS_PER_CELL.get(type);
    }

    public void removeDeadAnimals() {
        animals.removeIf(animal -> !animal.isAlive());
    }

    public void removeDeadPlants() {
        plants.removeIf(plant -> !plant.isAlive());
        plantCount.set(plants.size());
    }

    public void growPlants() {
        if (plantCount.get() < SimulationConfig.MAX_PLANTS_PER_CELL) {
            if (ThreadLocalRandom.current().nextDouble() < SimulationConfig.PLANT_GROWTH_PROBABILITY) {
                plants.add(new Plant());
                plantCount.incrementAndGet();
            }
        }
    }

    public List<Animal> getAliveAnimals() {
        return animals.stream()
                .filter(Animal::isAlive)
                .collect(Collectors.toList());
    }

    public List<Plant> getAlivePlants() {
        return plants.stream()
                .filter(Plant::isAlive)
                .collect(Collectors.toList());
    }

    // Геттеры
    public List<Animal> getAnimals() { return animals; }
    public List<Plant> getPlants() { return plants; }
    public int getPlantCount() { return plantCount.get(); }
    public Location getLocation() { return location; }

    public Map<AnimalType, Long> getAnimalCounts() {
        return animals.stream()
                .filter(Animal::isAlive)
                .collect(Collectors.groupingBy(Animal::getType, Collectors.counting()));
    }
}