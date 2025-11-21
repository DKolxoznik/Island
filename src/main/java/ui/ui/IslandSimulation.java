package main.java.ui.ui;

import main.java.model.Island;
import main.java.engine.SimulationEngine;
import main.java.config.SimulationConfig;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class IslandSimulation extends JFrame {
    private final Island island;
    private final SimulationEngine engine;
    private IslandPanel islandPanel;
    private StatsPanel statsPanel;
    private ControlPanel controlPanel;
    private Timer uiUpdateTimer;

    public IslandSimulation() {
        this.islandPanel = islandPanel;
        this.statsPanel = statsPanel;
        this.controlPanel = controlPanel;
        this.uiUpdateTimer = uiUpdateTimer;
        this.island = new Island(SimulationConfig.ISLAND_WIDTH, SimulationConfig.ISLAND_HEIGHT);
        this.engine = new SimulationEngine(island);

        initializeUI();
        setupUITimer();
    }

    private void initializeUI() {
        setTitle("Island Ecosystem Simulation 🏝️");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.islandPanel = new IslandPanel(island);
        this.statsPanel = new StatsPanel(island, engine);
        this.controlPanel = new ControlPanel(engine);

        // Создаем разделитель для изменения размера
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(islandPanel),
                statsPanel
        );
        splitPane.setDividerLocation(700);
        splitPane.setResizeWeight(1.0);

        add(splitPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void setupUITimer() {
        uiUpdateTimer = new Timer(true);
        uiUpdateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    islandPanel.repaint();
                    statsPanel.updateStats();
                    controlPanel.updateStatus();
                });
            }
        }, 0, SimulationConfig.UI_UPDATE_MS);
    }

    @Override
    public void dispose() {
        if (uiUpdateTimer != null) {
            uiUpdateTimer.cancel();
        }
        if (engine != null) {
            engine.stop();
        }
        super.dispose();
    }
}