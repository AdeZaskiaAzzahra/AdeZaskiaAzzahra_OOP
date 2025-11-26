package com.ade.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Coin {
    private Vector2 position;
    private Rectangle collider;
    private float radius = 15f;
    private boolean active;

    private float  bobOffset = 0f;
    private float  bobRadius = 2f;

    public coin(Vector2 startPositio) {
        this.position = new Vector2(startPositio);
        this.collider = new Rectangle(position.x - radius, position.y - radius, radius * 2, radius * 2);
        this.active   = false;
    }
    public void update(float delta) {
        if (!active) return;

        bobOffset += bobOffset * delta;
        collider.setPosition(position.x - radius, position.y - radius);
    }

    public void render(ShapeRenderer shapeRenderer) {
        if (!active) return;
        float  drawY = position.y + (float)(Math.sin(bobOffset) * 5f);

        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(position.x, drawY, radius);
    }

    public boolean isColliding(Rectangle playerCollider){
        return active && playerCollider.overlaps(collider);
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }

    public void setPosition(float x, float y) {
       this.position.set(x, y);
       collider.setPosition(x - radius, y - radius);
   }

   public Rectangle getCollider() {
        return collider;
   }

}

