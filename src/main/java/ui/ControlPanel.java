package ui;

import engine.SimulationEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final SimulationEngine engine;
    private final JButton startButton;
    private final JButton pauseButton;
    private final JButton stopButton;
    private final JLabel statusLabel;

    public ControlPanel(SimulationEngine engine) {
        this.engine = engine;

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(800, 60));
        setBackground(new Color(220, 220, 220));

        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        statusLabel = new JLabel("Status: STOPPED");

        // Настройка кнопок
        startButton.addActionListener(this::startSimulation);
        pauseButton.addActionListener(this::pauseSimulation);
        stopButton.addActionListener(this::stopSimulation);

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        add(startButton);
        add(pauseButton);
        add(stopButton);
        add(Box.createHorizontalStrut(20));
        add(statusLabel);
    }

    private void startSimulation(ActionEvent e) {
        engine.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
        statusLabel.setText("Status: RUNNING");
    }

    private void pauseSimulation(ActionEvent e) {
        if (engine.isPaused()) {
            engine.resume();
            pauseButton.setText("Pause");
            statusLabel.setText("Status: RUNNING");
        } else {
            engine.pause();
            pauseButton.setText("Resume");
            statusLabel.setText("Status: PAUSED");
        }
    }

    private void stopSimulation(ActionEvent e) {
        engine.stop();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        pauseButton.setText("Pause");
        statusLabel.setText("Status: STOPPED");
    }

    public void updateStatus() {
        SwingUtilities.invokeLater(() -> {
            if (!engine.isRunning()) {
                statusLabel.setText("Status: STOPPED");
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                stopButton.setEnabled(false);
            } else if (engine.isPaused()) {
                statusLabel.setText("Status: PAUSED");
                pauseButton.setText("Resume");
            } else {
                statusLabel.setText("Status: RUNNING");
                pauseButton.setText("Pause");
            }
        });
    }
}