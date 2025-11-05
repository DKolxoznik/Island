package engine;

import model.*;
import config.SimulationConfig;
import config.AnimalType;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationEngine {
    private final Island island;
    private final ScheduledExecutorService scheduler;
    private final ExecutorService animalProcessor;
    private final AtomicBoolean isRunning;
    private final AtomicBoolean isPaused;
    private final List<Animal> allAnimals;

    public SimulationEngine(Island island) {
        this.island = island;
        this.scheduler = Executors.newScheduledThreadPool(2);
        this.animalProcessor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.isRunning = new AtomicBoolean(false);
        this.isPaused = new AtomicBoolean(false);
        this.allAnimals = new CopyOnWriteArrayList<>();

        initializeAnimals();
    }

    private void initializeAnimals() {
        Random random = new Random();

        for (Map.Entry<AnimalType, Integer> entry : SimulationConfig.INITIAL_ANIMAL_COUNTS.entrySet()) {
            AnimalType type = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; i++) {
                int x = random.nextInt(island.getWidth());
                int y = random.nextInt(island.getHeight());
                Location location = new Location(x, y);

                Animal animal = createAnimal(type, location);
                if (animal != null) {
                    island.addAnimal(animal);
                    allAnimals.add(animal);
                }
            }
        }
    }

    private Animal createAnimal(AnimalType type, Location location) {
        switch (type) {
            case WOLF: return new model.animals.Wolf(location);
            case PYTHON: return new model.animals.Python(location);
            case FOX: return new model.animals.Fox(location);
            case BEAR: return new model.animals.Bear(location);
            case EAGLE: return new model.animals.Eagle(location);
            case HORSE: return new model.animals.Horse(location);
            case DEER: return new model.animals.Deer(location);
            case RABBIT: return new model.animals.Rabbit(location);
            case MOUSE: return new model.animals.Mouse(location);
            case GOAT: return new model.animals.Goat(location);
            case SHEEP: return new model.animals.Sheep(location);
            case BOAR: return new model.animals.Boar(location);
            case BUFFALO: return new model.animals.Buffalo(location);
            case DUCK: return new model.animals.Duck(location);
            case CATERPILLAR: return new model.animals.Caterpillar(location);
            default: return null;
        }
    }

    public void start() {
        if (isRunning.get()) return;

        isRunning.set(true);
        isPaused.set(false);

        // Запускаем основной цикл симуляции
        scheduler.scheduleAtFixedRate(this::processSimulationTick,
                0, SimulationConfig.SIMULATION_TICK_MS, TimeUnit.MILLISECONDS);
    }

    public void pause() {
        isPaused.set(true);
    }

    public void resume() {
        isPaused.set(false);
    }

    public void stop() {
        isRunning.set(false);
        isPaused.set(false);

        scheduler.shutdown();
        animalProcessor.shutdown();

        try {
            if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            if (!animalProcessor.awaitTermination(1, TimeUnit.SECONDS)) {
                animalProcessor.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            animalProcessor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void processSimulationTick() {
        if (!isRunning.get() || isPaused.get()) return;

        try {
            // Обрабатываем остров
            island.processTick();

            // Обрабатываем животных в параллельных потоках
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (Animal animal : allAnimals) {
                if (animal.isAlive()) {
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        processAnimal(animal);
                    }, animalProcessor);

                    futures.add(future);
                }
            }

            // Ждем завершения всех задач
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            // Убираем мертвых животных
            island.cleanupDeadAnimals();
            allAnimals.removeIf(animal -> !animal.isAlive());

        } catch (Exception e) {
            System.err.println("Error in simulation tick: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processAnimal(Animal animal) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Location oldLocation = animal.getLocation();
        Cell currentCell = island.getCell(oldLocation);

        if (currentCell == null || !animal.isAlive()) return;

        // Пытаемся поесть
        tryToEat(animal, currentCell, random);

        // Уменьшаем сытость
        animal.decreaseSatiety();

        // Пытаемся размножиться
        if (animal.isAlive()) {
            animal.tryReproduce(island, random);
        }

        // Двигаемся
        if (animal.isAlive()) {
            Location newLocation = animal.move(island, random);
            if (!newLocation.equals(oldLocation)) {
                island.moveAnimal(animal, oldLocation, newLocation);
            }
        }
    }

    private void tryToEat(Animal animal, Cell cell, ThreadLocalRandom random) {
        // Пытаемся съесть растения
        for (Plant plant : cell.getAlivePlants()) {
            animal.eat(plant, random);
            if (!animal.isAlive()) break;
        }

        // Пытаемся съесть других животных
        if (animal.isAlive()) {
            for (Animal prey : cell.getAliveAnimals()) {
                if (prey != animal && prey.isAlive()) {
                    animal.eat(prey, random);
                    if (!animal.isAlive() || !prey.isAlive()) break;
                }
            }
        }
    }

    // Геттеры
    public Island getIsland() { return island; }
    public boolean isRunning() { return isRunning.get(); }
    public boolean isPaused() { return isPaused.get(); }
    public int getAnimalCount() { return allAnimals.size(); }
}