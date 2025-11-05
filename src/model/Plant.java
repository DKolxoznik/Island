package model;

import config.SimulationConfig;

public class Plant {
    private boolean alive;

    public Plant() {
        this.alive = true;
    }

    public double getNutritionalValue() {
        return alive ? SimulationConfig.PLANT_NUTRITIONAL_VALUE : 0;
    }

    public void consume() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}