package ui;

import model.*;
import engine.SimulationEngine;
import config.AnimalType;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class StatsPanel extends JPanel {
    private final Island island;
    private final SimulationEngine engine;
    private final JTextArea statsText;
    private final JScrollPane scrollPane;

    public StatsPanel(Island island, SimulationEngine engine) {
        this.island = island;
        this.engine = engine;

        setPreferredSize(new Dimension(300, 600));
        setLayout(new BorderLayout());

        statsText = new JTextArea();
        statsText.setEditable(false);
        statsText.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statsText.setBackground(new Color(240, 240, 240));

        scrollPane = new JScrollPane(statsText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(new JLabel("Statistics", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        updateStats();
    }

    public void updateStats() {
        StringBuilder sb = new StringBuilder();

        sb.append("Tick: ").append(island.getTickCount()).append("\n");
        sb.append("Status: ").append(getStatusString()).append("\n");
        sb.append("Animals: ").append(island.getTotalAnimalCount()).append("\n");
        sb.append("Plants: ").append(island.getTotalPlantCount()).append("\n\n");

        sb.append("=== PREDATORS ===\n");
        Map<AnimalType, Long> counts = island.getTotalAnimalCounts();

        // Хищники
        appendAnimalStat(sb, "Wolf", AnimalType.WOLF, counts);
        appendAnimalStat(sb, "Python", AnimalType.PYTHON, counts);
        appendAnimalStat(sb, "Fox", AnimalType.FOX, counts);
        appendAnimalStat(sb, "Bear", AnimalType.BEAR, counts);
        appendAnimalStat(sb, "Eagle", AnimalType.EAGLE, counts);

        sb.append("\n=== HERBIVORES ===\n");
        // Травоядные
        appendAnimalStat(sb, "Horse", AnimalType.HORSE, counts);
        appendAnimalStat(sb, "Deer", AnimalType.DEER, counts);
        appendAnimalStat(sb, "Rabbit", AnimalType.RABBIT, counts);
        appendAnimalStat(sb, "Mouse", AnimalType.MOUSE, counts);
        appendAnimalStat(sb, "Goat", AnimalType.GOAT, counts);
        appendAnimalStat(sb, "Sheep", AnimalType.SHEEP, counts);
        appendAnimalStat(sb, "Boar", AnimalType.BOAR, counts);
        appendAnimalStat(sb, "Buffalo", AnimalType.BUFFALO, counts);
        appendAnimalStat(sb, "Duck", AnimalType.DUCK, counts);
        appendAnimalStat(sb, "Caterpillar", AnimalType.CATERPILLAR, counts);

        statsText.setText(sb.toString());

        // Авто-скролл вниз
        statsText.setCaretPosition(statsText.getDocument().getLength());
    }

    private void appendAnimalStat(StringBuilder sb, String name, AnimalType type, Map<AnimalType, Long> counts) {
        long count = counts.getOrDefault(type, 0L);
        String symbol = config.SimulationConfig.ANIMAL_SYMBOLS.get(type);
        sb.append(String.format("%s %-12s: %4d\n", symbol, name, count));
    }

    private String getStatusString() {
        if (!engine.isRunning()) {
            return "STOPPED";
        } else if (engine.isPaused()) {
            return "PAUSED";
        } else {
            return "RUNNING";
        }
    }
}