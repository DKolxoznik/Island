import ui.IslandSimulation;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            IslandSimulation simulation = new IslandSimulation();
            simulation.setVisible(true);
        });
    }
}