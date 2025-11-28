package com.ade.frontend.factories;

import com.ade.frontend.Coin;
import com.ade.frontend.pools.CoinPool;
import com.badlogic.gdx.utils.Array;

public class CoinFactory {

    public final CoinPool coinPool;
    private final Array<Coin> activeCoins;

    public CoinFactory() {
        this.coinPool = new CoinPool();
        this.activeCoins = new Array<>();
    }

    public void addCoin(Coin coin) {
        activeCoins.add(coin);
    }

    public Array<Coin> getActiveCoins() {
        return activeCoins;
    }

    public void releaseCoin(Coin coin) {
        coin.setActive(false);
        activeCoins.removeValue(coin, true);
        coinPool.free(coin);
    }

    public void releaseAll() {
        for (Coin c : activeCoins) {
            coinPool.free(c);
        }
        activeCoins.clear();
    }
}
