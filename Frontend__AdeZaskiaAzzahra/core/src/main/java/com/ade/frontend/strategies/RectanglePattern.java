package com.ade.frontend.strategies;

import com.ade.frontend.Coin;
import com.ade.frontend.factories.CoinFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RectanglePattern implements CoinPattern {

    private static final float SPACING_X = 40f;
    private static final float SPACING_Y = 40f;
    private final Random random = new Random();

    @Override
    public List<Coin> spawn(CoinFactory factory, float groundTopY, float spawnX, float screenHeight) {

        List<Coin> coins = new ArrayList<>();

        int cols = 3 + random.nextInt(2); // 3-4
        int rows = 2 + random.nextInt(2); // 2-3

        float minY = groundTopY + 50;
        float maxY = screenHeight - 100;

        float startY = minY + random.nextFloat() * (maxY - minY);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                float x = spawnX + c * SPACING_X;
                float y = startY + r * SPACING_Y;

                Coin coin = factory.coinPool.obtain(x, y);
                coins.add(coin);
            }
        }

        return coins;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }
}
