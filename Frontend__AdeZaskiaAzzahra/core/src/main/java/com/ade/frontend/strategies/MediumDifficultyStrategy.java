package com.ade.frontend.strategies;

import java.util.Map;
import java.util.HashMap;

public class MediumDifficultyStrategy implements DifficultyStrategy {

    @Override
    public float getSpawnInterval() {
        return 1.4f;
    }

    @Override
    public int getDensity() {
        return 2;
    }

    @Override
    public float getMinGap() {
        return 300f;
    }

    @Override
    public Map<String, Integer> getObstacleWeights() {
        Map<String, Integer> w = new HashMap<>();
        w.put("VerticalLaser", 2);
        w.put("HorizontalLaser", 2);
        w.put("HomingMissile", 1);
        return w;
    }
}
