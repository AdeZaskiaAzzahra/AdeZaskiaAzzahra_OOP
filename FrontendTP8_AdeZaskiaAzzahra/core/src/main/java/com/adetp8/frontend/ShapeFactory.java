package com.adetp8.frontend;

public class ShapeFactory {
    private ShapePool pool;

    public ShapeFactory(ShapePool pool) {
        this.pool = pool;
    }

    public Shape createShape(String type) {
        Shape shape = pool.obtain(type);
        if (shape == null) {
            if (type.equalsIgnoreCase("Circle")) {
                shape = new Circle();
            } else if (type.equalsIgnoreCase("Square")) {
                shape = new Square();
            }
        }
        return shape;
    }
}
