package ui;

import model.*;
import config.SimulationConfig;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class IslandPanel extends JPanel {
    private final Island island;

    public IslandPanel(Island island) {
        this.island = island;
        setPreferredSize(new Dimension(
                SimulationConfig.ISLAND_WIDTH * SimulationConfig.CELL_SIZE,
                SimulationConfig.ISLAND_HEIGHT * SimulationConfig.CELL_SIZE
        ));
        setBackground(new Color(34, 139, 34)); // Зеленый фон
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Рисуем растения
        drawPlants(g2d);

        // Рисуем животных
        drawAnimals(g2d);

        // Рисуем сетку
        drawGrid(g2d);
    }

    private void drawPlants(Graphics2D g2d) {
        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                Cell cell = island.getCell(x, y);
                if (cell != null) {
                    int plantCount = cell.getPlantCount();

                    if (plantCount > 0) {
                        int intensity = Math.min(255, plantCount);
                        g2d.setColor(new Color(0, Math.min(255, 100 + intensity / 2), 0, 100));
                        g2d.fillRect(
                                x * SimulationConfig.CELL_SIZE,
                                y * SimulationConfig.CELL_SIZE,
                                SimulationConfig.CELL_SIZE,
                                SimulationConfig.CELL_SIZE
                        );
                    }
                }
            }
        }
    }

    private void drawAnimals(Graphics2D g2d) {
        Font originalFont = g2d.getFont();
        g2d.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));

        for (int x = 0; x < island.getWidth(); x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                Cell cell = island.getCell(x, y);
                if (cell != null) {
                    for (Animal animal : cell.getAliveAnimals()) {
                        drawAnimal(g2d, animal, x, y);
                    }
                }
            }
        }

        g2d.setFont(originalFont);
    }

    private void drawAnimal(Graphics2D g2d, Animal animal, int cellX, int cellY) {
        String symbol = SimulationConfig.ANIMAL_SYMBOLS.get(animal.getType());
        Color color = SimulationConfig.ANIMAL_COLORS.get(animal.getType());

        // Прозрачность зависит от сытости
        int alpha = (int) (255 * animal.getSatietyPercent());
        alpha = Math.max(50, Math.min(255, alpha));
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);

        g2d.setColor(color);

        // Случайное смещение в пределах клетки для естественного вида
        int offsetX = ThreadLocalRandom.current().nextInt(4);
        int offsetY = ThreadLocalRandom.current().nextInt(4);

        g2d.drawString(
                symbol,
                cellX * SimulationConfig.CELL_SIZE + offsetX + 4,
                cellY * SimulationConfig.CELL_SIZE + offsetY + 12
        );
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 30));

        for (int x = 0; x <= island.getWidth(); x++) {
            g2d.drawLine(
                    x * SimulationConfig.CELL_SIZE, 0,
                    x * SimulationConfig.CELL_SIZE, island.getHeight() * SimulationConfig.CELL_SIZE
            );
        }

        for (int y = 0; y <= island.getHeight(); y++) {
            g2d.drawLine(
                    0, y * SimulationConfig.CELL_SIZE,
                    island.getWidth() * SimulationConfig.CELL_SIZE, y * SimulationConfig.CELL_SIZE
            );
        }
    }
}