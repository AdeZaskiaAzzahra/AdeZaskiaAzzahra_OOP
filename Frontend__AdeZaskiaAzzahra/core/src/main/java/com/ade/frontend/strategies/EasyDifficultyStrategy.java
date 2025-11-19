package com.ade.frontend.strategies;

import java.util.Map;
import java.util.HashMap;

public class EasyDifficultyStrategy implements DifficultyStrategy {

    @Override
    public float getSpawnInterval() {
        return 2.0f;
    }

    @Override
    public int getDensity() {
        return 1;
    }

    @Override
    public float getMinGap() {
        return 350f;
    }

    @Override
    public Map<String, Integer> getObstacleWeights() {
        Map<String, Integer> w = new HashMap<>();
        w.put("VerticalLaser", 1);
        w.put("HorizontalLaser", 1);
        return w;
    }
}
