package com.ade.frontend.pools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ade.frontend.obstacles.VerticalLaser;

public class VerticalLaserPool extends ObjectPool<VerticalLaser> {

    @Override
    protected VerticalLaser createObject(){
        return new VerticalLaser(new Vector2(Gdx.graphics.getWidth(), 0), 100);
    }
    @Override
    protected void resetObject(VerticalLaser laser) {
        laser.setPosition(Gdx.graphics.getWidth(), 0);
        laser.setActive(false);
    }

    public VerticalLaser obtain(Vector2 position, int length) {
        VerticalLaser laser = super.obtain();
        laser.initialize(position, length);
        laser.setActive(true);
        return laser;
    }
}
