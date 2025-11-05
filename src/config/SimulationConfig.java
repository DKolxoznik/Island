package config;

import java.util.Map;

public class SimulationConfig {
    // Размеры острова
    public static final int ISLAND_WIDTH = 50;
    public static final int ISLAND_HEIGHT = 35;
    public static final int CELL_SIZE = 20;

    // Временные параметры
    public static final int SIMULATION_TICK_MS = 500;
    public static final int UI_UPDATE_MS = 100;

    // Растения
    public static final int MAX_PLANTS_PER_CELL = 200;
    public static final double PLANT_GROWTH_PROBABILITY = 0.3;
    public static final double PLANT_NUTRITIONAL_VALUE = 1.0;
    public static final int INITIAL_PLANTS_MIN = 10;
    public static final int INITIAL_PLANTS_MAX = 50;

    // Параметры животных
    public static final Map<AnimalType, Double> ANIMAL_WEIGHTS = Map.ofEntries(
            Map.entry(AnimalType.WOLF, 50.0),
            Map.entry(AnimalType.PYTHON, 15.0),
            Map.entry(AnimalType.FOX, 8.0),
            Map.entry(AnimalType.BEAR, 500.0),
            Map.entry(AnimalType.EAGLE, 6.0),
            Map.entry(AnimalType.HORSE, 400.0),
            Map.entry(AnimalType.DEER, 300.0),
            Map.entry(AnimalType.RABBIT, 2.0),
            Map.entry(AnimalType.MOUSE, 0.05),
            Map.entry(AnimalType.GOAT, 60.0),
            Map.entry(AnimalType.SHEEP, 70.0),
            Map.entry(AnimalType.BOAR, 400.0),
            Map.entry(AnimalType.BUFFALO, 700.0),
            Map.entry(AnimalType.DUCK, 1.0),
            Map.entry(AnimalType.CATERPILLAR, 0.01)
    );

    public static final Map<AnimalType, Integer> MAX_ANIMALS_PER_CELL = Map.ofEntries(
            Map.entry(AnimalType.WOLF, 30),
            Map.entry(AnimalType.PYTHON, 30),
            Map.entry(AnimalType.FOX, 30),
            Map.entry(AnimalType.BEAR, 5),
            Map.entry(AnimalType.EAGLE, 20),
            Map.entry(AnimalType.HORSE, 20),
            Map.entry(AnimalType.DEER, 20),
            Map.entry(AnimalType.RABBIT, 150),
            Map.entry(AnimalType.MOUSE, 500),
            Map.entry(AnimalType.GOAT, 140),
            Map.entry(AnimalType.SHEEP, 140),
            Map.entry(AnimalType.BOAR, 50),
            Map.entry(AnimalType.BUFFALO, 10),
            Map.entry(AnimalType.DUCK, 200),
            Map.entry(AnimalType.CATERPILLAR, 1000)
    );

    public static final Map<AnimalType, Integer> MOVEMENT_SPEED = Map.ofEntries(
            Map.entry(AnimalType.WOLF, 3),
            Map.entry(AnimalType.PYTHON, 1),
            Map.entry(AnimalType.FOX, 2),
            Map.entry(AnimalType.BEAR, 2),
            Map.entry(AnimalType.EAGLE, 3),
            Map.entry(AnimalType.HORSE, 4),
            Map.entry(AnimalType.DEER, 4),
            Map.entry(AnimalType.RABBIT, 2),
            Map.entry(AnimalType.MOUSE, 1),
            Map.entry(AnimalType.GOAT, 3),
            Map.entry(AnimalType.SHEEP, 3),
            Map.entry(AnimalType.BOAR, 2),
            Map.entry(AnimalType.BUFFALO, 3),
            Map.entry(AnimalType.DUCK, 4),
            Map.entry(AnimalType.CATERPILLAR, 0)
    );

    public static final Map<AnimalType, Double> MAX_FOOD_REQUIRED = Map.ofEntries(
            Map.entry(AnimalType.WOLF, 8.0),
            Map.entry(AnimalType.PYTHON, 3.0),
            Map.entry(AnimalType.FOX, 2.0),
            Map.entry(AnimalType.BEAR, 80.0),
            Map.entry(AnimalType.EAGLE, 1.0),
            Map.entry(AnimalType.HORSE, 60.0),
            Map.entry(AnimalType.DEER, 50.0),
            Map.entry(AnimalType.RABBIT, 0.45),
            Map.entry(AnimalType.MOUSE, 0.01),
            Map.entry(AnimalType.GOAT, 10.0),
            Map.entry(AnimalType.SHEEP, 15.0),
            Map.entry(AnimalType.BOAR, 50.0),
            Map.entry(AnimalType.BUFFALO, 100.0),
            Map.entry(AnimalType.DUCK, 0.15),
            Map.entry(AnimalType.CATERPILLAR, 0.0)
    );

    // Вероятности размножения
    public static final Map<AnimalType, Double> REPRODUCTION_PROBABILITY = Map.ofEntries(
            Map.entry(AnimalType.WOLF, 0.05),
            Map.entry(AnimalType.PYTHON, 0.03),
            Map.entry(AnimalType.FOX, 0.07),
            Map.entry(AnimalType.BEAR, 0.02),
            Map.entry(AnimalType.EAGLE, 0.04),
            Map.entry(AnimalType.HORSE, 0.08),
            Map.entry(AnimalType.DEER, 0.09),
            Map.entry(AnimalType.RABBIT, 0.12),
            Map.entry(AnimalType.MOUSE, 0.15),
            Map.entry(AnimalType.GOAT, 0.1),
            Map.entry(AnimalType.SHEEP, 0.1),
            Map.entry(AnimalType.BOAR, 0.11),
            Map.entry(AnimalType.BUFFALO, 0.06),
            Map.entry(AnimalType.DUCK, 0.1),
            Map.entry(AnimalType.CATERPILLAR, 0.2)
    );

    // Количество детенышей
    public static final Map<AnimalType, Integer> OFFSPRING_COUNT = Map.ofEntries(
            Map.entry(AnimalType.WOLF, 3),
            Map.entry(AnimalType.PYTHON, 8),
            Map.entry(AnimalType.FOX, 4),
            Map.entry(AnimalType.BEAR, 2),
            Map.entry(AnimalType.EAGLE, 2),
            Map.entry(AnimalType.HORSE, 1),
            Map.entry(AnimalType.DEER, 1),
            Map.entry(AnimalType.RABBIT, 6),
            Map.entry(AnimalType.MOUSE, 8),
            Map.entry(AnimalType.GOAT, 2),
            Map.entry(AnimalType.SHEEP, 2),
            Map.entry(AnimalType.BOAR, 6),
            Map.entry(AnimalType.BUFFALO, 1),
            Map.entry(AnimalType.DUCK, 5),
            Map.entry(AnimalType.CATERPILLAR, 20)
    );

    // Начальное количество животных
    public static final Map<AnimalType, Integer> INITIAL_ANIMAL_COUNTS = Map.ofEntries(
            Map.entry(AnimalType.WOLF, 20),
            Map.entry(AnimalType.PYTHON, 30),
            Map.entry(AnimalType.FOX, 40),
            Map.entry(AnimalType.BEAR, 5),
            Map.entry(AnimalType.EAGLE, 15),
            Map.entry(AnimalType.HORSE, 30),
            Map.entry(AnimalType.DEER, 40),
            Map.entry(AnimalType.RABBIT, 100),
            Map.entry(AnimalType.MOUSE, 200),
            Map.entry(AnimalType.GOAT, 50),
            Map.entry(AnimalType.SHEEP, 60),
            Map.entry(AnimalType.BOAR, 40),
            Map.entry(AnimalType.BUFFALO, 10),
            Map.entry(AnimalType.DUCK, 80),
            Map.entry(AnimalType.CATERPILLAR, 300)
    );

    // Юникод символы для отображения
    public static final Map<AnimalType, String> ANIMAL_SYMBOLS = Map.ofEntries(
            Map.entry(AnimalType.WOLF, "🐺"),
            Map.entry(AnimalType.PYTHON, "🐍"),
            Map.entry(AnimalType.FOX, "🦊"),
            Map.entry(AnimalType.BEAR, "🐻"),
            Map.entry(AnimalType.EAGLE, "🦅"),
            Map.entry(AnimalType.HORSE, "🐎"),
            Map.entry(AnimalType.DEER, "🦌"),
            Map.entry(AnimalType.RABBIT, "🐇"),
            Map.entry(AnimalType.MOUSE, "🐁"),
            Map.entry(AnimalType.GOAT, "🐐"),
            Map.entry(AnimalType.SHEEP, "🐑"),
            Map.entry(AnimalType.BOAR, "🐗"),
            Map.entry(AnimalType.BUFFALO, "🐃"),
            Map.entry(AnimalType.DUCK, "🦆"),
            Map.entry(AnimalType.CATERPILLAR, "🐛")
    );

    // Цвета для отображения
    public static final Map<AnimalType, java.awt.Color> ANIMAL_COLORS = Map.ofEntries(
            Map.entry(AnimalType.WOLF, new java.awt.Color(128, 128, 128)),
            Map.entry(AnimalType.PYTHON, new java.awt.Color(0, 128, 0)),
            Map.entry(AnimalType.FOX, new java.awt.Color(255, 165, 0)),
            Map.entry(AnimalType.BEAR, new java.awt.Color(139, 69, 19)),
            Map.entry(AnimalType.EAGLE, new java.awt.Color(101, 67, 33)),
            Map.entry(AnimalType.HORSE, new java.awt.Color(210, 180, 140)),
            Map.entry(AnimalType.DEER, new java.awt.Color(160, 120, 80)),
            Map.entry(AnimalType.RABBIT, new java.awt.Color(255, 255, 255)),
            Map.entry(AnimalType.MOUSE, new java.awt.Color(192, 192, 192)),
            Map.entry(AnimalType.GOAT, new java.awt.Color(245, 245, 220)),
            Map.entry(AnimalType.SHEEP, new java.awt.Color(250, 250, 250)),
            Map.entry(AnimalType.BOAR, new java.awt.Color(105, 105, 105)),
            Map.entry(AnimalType.BUFFALO, new java.awt.Color(101, 67, 33)),
            Map.entry(AnimalType.DUCK, new java.awt.Color(255, 215, 0)),
            Map.entry(AnimalType.CATERPILLAR, new java.awt.Color(50, 205, 50))
    );

    public static double getEatingProbability(AnimalType predator, AnimalType prey) {
        Map<AnimalType, Map<AnimalType, Double>> probabilities = Map.of(
                AnimalType.WOLF, Map.of(
                        AnimalType.HORSE, 10.0, AnimalType.DEER, 15.0, AnimalType.RABBIT, 60.0,
                        AnimalType.MOUSE, 80.0, AnimalType.GOAT, 60.0, AnimalType.SHEEP, 70.0,
                        AnimalType.BOAR, 15.0, AnimalType.BUFFALO, 10.0, AnimalType.DUCK, 40.0
                ),
                AnimalType.PYTHON, Map.of(
                        AnimalType.FOX, 15.0, AnimalType.RABBIT, 20.0, AnimalType.MOUSE, 40.0,
                        AnimalType.DUCK, 10.0
                ),
                AnimalType.FOX, Map.of(
                        AnimalType.RABBIT, 70.0, AnimalType.MOUSE, 90.0, AnimalType.DUCK, 60.0,
                        AnimalType.CATERPILLAR, 40.0
                ),
                AnimalType.BEAR, Map.of(
                        AnimalType.PYTHON, 80.0, AnimalType.HORSE, 40.0, AnimalType.DEER, 80.0,
                        AnimalType.RABBIT, 80.0, AnimalType.MOUSE, 90.0, AnimalType.GOAT, 70.0,
                        AnimalType.SHEEP, 70.0, AnimalType.BOAR, 50.0, AnimalType.BUFFALO, 20.0,
                        AnimalType.DUCK, 10.0
                ),
                AnimalType.EAGLE, Map.of(
                        AnimalType.FOX, 10.0, AnimalType.RABBIT, 90.0, AnimalType.MOUSE, 90.0,
                        AnimalType.DUCK, 80.0
                ),
                AnimalType.BOAR, Map.of(
                        AnimalType.MOUSE, 50.0, AnimalType.CATERPILLAR, 90.0
                ),
                AnimalType.DUCK, Map.of(
                        AnimalType.CATERPILLAR, 90.0
                ),
                AnimalType.MOUSE, Map.of(
                        AnimalType.CATERPILLAR, 90.0
                )
        );

        Map<AnimalType, Double> predatorProbs = probabilities.get(predator);
        return predatorProbs != null ? predatorProbs.getOrDefault(prey, 0.0) / 100.0 : 0.0;
    }
}