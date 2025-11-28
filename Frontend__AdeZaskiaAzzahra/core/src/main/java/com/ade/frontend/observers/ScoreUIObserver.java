package com.ade.frontend.observers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreUIObserver implements Observer {

    private BitmapFont font;
    private SpriteBatch batch;

    public ScoreUIObserver() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        batch = new SpriteBatch();
    }

    @Override
    public void update(int score) {
        System.out.println("Score updated: " + score);
    }

    public void render(int score, int coins) {
        batch.begin();
        font.draw(batch, "Score: " + score, 20, 720);
        font.draw(batch, "Coins: " + coins, 20, 680);
        batch.end();
    }

    public void render(int score) {
        batch.begin();
        font.draw(batch, "Score: " + score, 20, 720);
        batch.end();
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
