package com.ade.frontend.factories;

import com.ade.frontend.Coin;
import com.ade.frontend.pools.CoinPool;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class CoinFactory {

   private CoinPool coinPool;
   private Random random;
   private Array<Coin> activeCoins;

   public CoinFactory() {
       coinPool = new CoinPool();
       random = new Random();
       activeCoins = new Array<>();
   }

       public void createCoinPattern(float spawnX, float groundTopY) {
       if (random.nextFloat() > 0.3f) return;

       for (int i = 0; i < 3; i++) {
           Coin coin = coinPool.obtain(spawnX + (i * 40), groundTopY + 60);
           activeCoins.add(coin);
       }
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
