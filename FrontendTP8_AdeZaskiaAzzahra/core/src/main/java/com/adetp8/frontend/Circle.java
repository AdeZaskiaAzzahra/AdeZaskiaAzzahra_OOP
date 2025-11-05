package com.adetp8.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle extends Shape {

    public Circle() {
        super("Circle");
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(position.x, position.y, size/ 2);
    }
}


