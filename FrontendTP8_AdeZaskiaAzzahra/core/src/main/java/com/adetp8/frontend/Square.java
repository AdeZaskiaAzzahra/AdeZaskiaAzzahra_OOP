package com.adetp8.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Square extends Shape {

    public Square() {
        super("Square");
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x - size / 2, position.y - size / 2, size, size);
    }
}
