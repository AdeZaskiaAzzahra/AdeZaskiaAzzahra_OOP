package com.ade.frontend.strategies;

import java.util.Map;
import java.util.HashMap;

public class HardDifficultyStrategy implements DifficultyStrategy {

    @Override
    public float getSpawnInterval() {
        return 1.0f;
    }

    @Override
    public int getDensity() {
        return 3;
    }

    @Override
    public float getMinGap() {
        return 250f;
    }

    @Override
    public Map<String, Integer> getObstacleWeights() {
        Map<String, Integer> w = new HashMap<>();
        w.put("VerticalLaser", 3);
        w.put("HorizontalLaser", 3);
        w.put("HomingMissile", 4);
        return w;
    }
}
