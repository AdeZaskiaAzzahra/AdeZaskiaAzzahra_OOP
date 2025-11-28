package com.ade.frontend.strategies;

import com.ade.frontend.Coin;
import com.ade.frontend.factories.CoinFactory;

import java.util.List;

public interface CoinPattern {
    List<Coin> spawn(CoinFactory factory, float groundTopY, float spawnX, float screenHeight);
    String getName();
}
