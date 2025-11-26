package com.ade.frontend.factories;

import com.ade.frontend.Coin;
import com.ade.frontend.pools.CoinPool;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class CoinFactory {
    private CoinPool coinPool;
    private Random random;
    private Array<Coin> activeCoins;

    public CoinFactory(){
        coinPool = new CoinPool();
        random = new Random();
        activeCoins = new Array<>();
    }
    

}
