package com.ade.frontend.pools;

import com.ade.frontend.Coin;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class CoinPool extends Pool<Coin> {

   @Override
   protected Coin newObject() {
       return new Coin(new Vector2(0, 0));
   }

   @Override
   protected void reset(Coin coin) {
       coin.setActive(false);
   }

       public Coin obtain(float x, float y) {
       Coin coin = super.obtain();
       coin.setPosition(x, y);
       coin.setActive(true);
       return coin;
   }
}
