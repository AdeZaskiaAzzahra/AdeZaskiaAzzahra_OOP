package com.adetp8.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Shape {
    protected Vector2 position;
    protected float size = 50f;
    protected String type;

    public Shape(String type) {
        this.type = type;
        this.position = new Vector2(0, 0);
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public String getType() {
        return type;
    }

    public abstract void render(ShapeRenderer shapeRenderer);
}
