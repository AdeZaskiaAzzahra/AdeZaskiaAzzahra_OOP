package com.ade.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ade.frontend.strategies.DifficultyStrategy;

public class DifficultyTransitionState implements GameState {

    private final GameStateManager gsm;
    private final PlayingState playingState;
    private final DifficultyStrategy newStrategy;
    private final BitmapFont font;
    private float timer = 2.0f;

    public DifficultyTransitionState(GameStateManager gsm, PlayingState play, DifficultyStrategy strategy) {
        this.gsm = gsm;
        this.playingState = play;
        this.newStrategy = strategy;
        this.font = new BitmapFont();
    }

    @Override
    public void update(float delta) {
        timer -= delta;
        if (timer <= 0) {
            playingState.setDifficulty(newStrategy);
            gsm.pop();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        playingState.render(batch);

        batch.begin();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        font.draw(batch, "DIFFICULTY INCREASED!", w / 2f - 80, h / 2f);
        font.draw(batch, newStrategy.getClass().getSimpleName(), w / 2f - 70, h / 2f - 40);
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
