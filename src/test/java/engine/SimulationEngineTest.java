package engine;

import model.Island;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void testEngineInitialization() {
        Island island = new Island(10, 10);
        SimulationEngine engine = new SimulationEngine(island);

        assertNotNull(engine);
        assertNotNull(engine.getIsland());
        assertFalse(engine.isRunning());
        assertFalse(engine.isPaused());
    }

    @Test
    void testStartStopEngine() throws InterruptedException {
        Island island = new Island(5, 5);
        SimulationEngine engine = new SimulationEngine(island);

        engine.start();
        assertTrue(engine.isRunning());

        Thread.sleep(100); // Даем немного времени

        engine.stop();
        assertFalse(engine.isRunning());
    }

    @Test
    void testPauseResumeEngine() {
        Island island = new Island(5, 5);
        SimulationEngine engine = new SimulationEngine(island);

        engine.start();
        engine.pause();
        assertTrue(engine.isPaused());

        engine.resume();
        assertFalse(engine.isPaused());

        engine.stop();
    }
}