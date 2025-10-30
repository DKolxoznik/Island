package model;

import config.SimulationConfig;
import config.AnimalType;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Island {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private final AtomicInteger tickCount;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
        this.tickCount = new AtomicInteger(0);
        initializeCells();
    }

    private void initializeCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(new Location(x, y));
            }
        }
    }

    public void addAnimal(Animal animal) {
        Location location = animal.getLocation();
        if (isValidLocation(location.getX(), location.getY())) {
            cells[location.getX()][location.getY()].addAnimal(animal);
        }
    }

    public boolean isValidLocation(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Cell getCell(Location location) {
        if (isValidLocation(location.getX(), location.getY())) {
            return cells[location.getX()][location.getY()];
        }
        return null;
    }

    public Cell getCell(int x, int y) {
        if (isValidLocation(x, y)) {
            return cells[x][y];
        }
        return null;
    }

    public void moveAnimal(Animal animal, Location oldLocation, Location newLocation) {
        Cell oldCell = getCell(oldLocation);
        Cell newCell = getCell(newLocation);

        if (oldCell != null && newCell != null && newCell.canAddAnimal(animal.getType())) {
            oldCell.getAnimals().remove(animal);
            newCell.addAnimal(animal);
            animal.setLocation(newLocation);
        }
    }

    public void processTick() {
        tickCount.incrementAndGet();

        // Обработка растений
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].growPlants();
                cells[x][y].removeDeadPlants();
            }
        }
    }

    public void cleanupDeadAnimals() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].removeDeadAnimals();
            }
        }
    }

    // Геттеры
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getTickCount() { return tickCount.get(); }
    public Cell[][] getCells() { return cells; }

    public Map<AnimalType, Long> getTotalAnimalCounts() {
        Map<AnimalType, Long> counts = new ConcurrentHashMap<>();

        // Инициализируем все типы нулями
        for (AnimalType type : AnimalType.values()) {
            counts.put(type, 0L);
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Map<AnimalType, Long> cellCounts = cells[x][y].getAnimalCounts();
                cellCounts.forEach((type, count) ->
                        counts.merge(type, count, Long::sum)
                );
            }
        }

        return counts;
    }

    public int getTotalPlantCount() {
        int total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total += cells[x][y].getPlantCount();
            }
        }
        return total;
    }

    public int getTotalAnimalCount() {
        int total = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                total += cells[x][y].getAliveAnimals().size();
            }
        }
        return total;
    }
}