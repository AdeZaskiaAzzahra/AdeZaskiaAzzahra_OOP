package com.ade.frontend;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Coin {

    private Vector2 position;
    private Rectangle collider;
    private float radius = 15f;
    private boolean active;

    private float bobTime = 0f;

    public Coin(Vector2 startPosition) {
        this.position = new Vector2(startPosition);
        this.collider = new Rectangle(
            position.x - radius,
            position.y - radius,
            radius * 2,
            radius * 2
        );
        this.active = false;
    }

    public void update(float delta) {
        if (!active) return;

        bobTime += delta * 4f;

        collider.setPosition(position.x - radius, position.y - radius);
    }


    public void renderShape(ShapeRenderer renderer) {
        if (!active) return;

        float bobOffset = (float) Math.sin(bobTime) * 5f;

        renderer.setColor(Color.YELLOW);
        renderer.circle(position.x, position.y + bobOffset, radius);
    }

    public boolean isOffScreenCamera(float cameraLeftEdge) {
        return (position.x + radius) < (cameraLeftEdge - 50);
    }

    public void render(SpriteBatch batch) {
    }

    public boolean isColliding(Rectangle playerCollider) {
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
