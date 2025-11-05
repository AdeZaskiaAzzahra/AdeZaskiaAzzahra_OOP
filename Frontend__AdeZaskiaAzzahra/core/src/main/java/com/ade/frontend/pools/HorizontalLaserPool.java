package com.ade.frontend.pools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ade.frontend.obstacles.HorizontalLaser;

public class HorizontalLaserPool extends ObjectPool<HorizontalLaser> {
  @Override
  protected HorizontalLaser createObject() {
      return new HorizontalLaser(new Vector2(Gdx.graphics.getWidth(), 0), 100);
  }

    @Override
    protected void resetObject(HorizontalLaser laser) {
        laser.setPosition(Gdx.graphics.getWidth(), 0);
        laser.setActive(false);
    }
  public HorizontalLaser obtain(Vector2 position, int length) {
      HorizontalLaser laser = super.obtain();
      laser.initialize(position, length);
      laser.setActive(true);
      return laser;
  }
}
