package com.ade.frontend.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ade.frontend.Player;

public class HomingMissile extends BaseObstacle {

    private Player target;
    private Vector2 velocity;

    private float speed = 200f;
    private float width = 40f;
    private float height = 20f;

    private TextureRegion texture;
    private float rotation = 0f;

    public HomingMissile(Vector2 startPosition) {
        super(startPosition, 0);
        this.velocity = new Vector2();

        Texture img = new Texture(Gdx.files.internal("missile.png"));
        this.texture = new TextureRegion(img);
    }

    @Override
    public void initialize(Vector2 startPosition, int length) {
        super.initialize(startPosition, length);
        this.velocity.set(0, 0);
        this.rotation = 0f;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public boolean isTargetingPlayer() {
        if (target == null) return false;

        return (target.getPosition().x + target.getWidth() / 2f)
            <= (position.x + width / 2f);
    }

    public void update(float delta) {
        if (target == null || !active) return;

        if (isTargetingPlayer()) {

            Vector2 toPlayer = new Vector2(
                target.getPosition().x - position.x,
                target.getPosition().y - position.y
            ).nor();

            velocity.set(toPlayer.x * speed, toPlayer.y * speed);

            rotation = toPlayer.angleDeg();

        }

        position.add(velocity.x * delta, velocity.y * delta);

        updateCollider();
    }

    @Override
    protected void updateCollider() {
        collider = new Rectangle(position.x, position.y, width, height);
    }

    @Override
    protected void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, width, height);
    }

    @Override
    protected float getRenderWidth() {
        return width;
    }

    public void render(SpriteBatch batch) {
        if (!active) return;

        batch.draw(
            texture,
            position.x, position.y,
            width / 2f, height / 2f,
            width, height,
            1f, 1f,
            rotation
        );
    }
}
