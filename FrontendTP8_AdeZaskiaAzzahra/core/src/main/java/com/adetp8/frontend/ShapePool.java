package com.adetp8.frontend;

import java.util.ArrayList;

public class ShapePool {
    private ArrayList<Shape> circlePool;
    private ArrayList<Shape> squarePool;

    public ShapePool() {
        circlePool = new ArrayList<>();
        squarePool = new ArrayList<>();
    }

    public Shape obtain(String type) {
        if (type.equalsIgnoreCase("Circle") && !circlePool.isEmpty()) {
            return circlePool.remove(0);
        } else if (type.equalsIgnoreCase("Square") && !squarePool.isEmpty()) {
            return squarePool.remove(0);
        }
        return null;
    }

    public void release(Shape shape) {
        if (shape.getType().equalsIgnoreCase("Circle") && circlePool.size() < 3) {
            circlePool.add(shape);
        } else if (shape.getType().equalsIgnoreCase("Square") && squarePool.size() < 3) {
            squarePool.add(shape);
        }
    }

    public ArrayList<Shape> getPool(String type) {
        if (type.equalsIgnoreCase("Circle")) return circlePool;
        else return squarePool;
    }
}
