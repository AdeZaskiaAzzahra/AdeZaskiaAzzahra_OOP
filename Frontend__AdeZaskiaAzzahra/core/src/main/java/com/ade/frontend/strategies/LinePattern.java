package com.ade.frontend.strategies;

import com.ade.frontend.Coin;
import com.ade.frontend.factories.CoinFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinePattern implements CoinPattern {

    private static final float SPACING = 40f;
    private final Random random = new Random();

    @Override
    public List<Coin> spawn(CoinFactory factory, float groundTopY, float spawnX, float screenHeight) {
        List<Coin> coins = new ArrayList<>();

        int count = 3 + random.nextInt(3);

        float minY = groundTopY + 50;
        float maxY = screenHeight - 100;

        float startY = minY + random.nextFloat() * (maxY - minY);

        for (int i = 0; i < count; i++) {
            Coin c = factory.coinPool.obtain(spawnX + i * SPACING, startY);
            coins.add(c);
        }
        return coins;
    }
    @Override
    public String getName(){
        return "Line";
    }

}
