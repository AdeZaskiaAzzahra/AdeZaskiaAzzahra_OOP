package com.ade.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Player player;
    private Ground ground;
    private GameManager gameManager;

    // Camera system
    private OrthographicCamera camera;
    private float cameraOffset = 0.2f;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        gameManager = GameManager.getInstance();

        // Initialize camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        player = new Player(new Vector2(100, Gdx.graphics.getHeight() / 2f));
        ground = new Ground();
        gameManager.startGame();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        // Update game logic
        update(delta);

        // Render
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Set camera for rendering
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Render ground (filled gray rectangle)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ground.renderShape(shapeRenderer);
        player.renderShape(shapeRenderer);
        shapeRenderer.end();
    }

    private void update(float delta) {
        boolean isFlying = Gdx.input.isKeyPressed(Input.Keys.SPACE);
        player.update(delta, isFlying);
        updateCamera(delta);

        // Update ground position based on camera BEFORE checking boundaries
        ground.update(camera.position.x);
        player.checkBoundaries(ground, Gdx.graphics.getHeight());

        // Calculate distance-based score and log changes
        int currentDistance = (int)player.getDistanceTraveled();
        int previousDistance = (int)(gameManager.getScore());

        if (currentDistance != previousDistance) {
            System.out.println("Distance: " + currentDistance + "m");
            gameManager.setScore(currentDistance);
        }
    }

    private void updateCamera(float delta) {
        float cameraFocus = player.getPosition().x + Gdx.graphics.getWidth() * cameraOffset;
        camera.position.x = cameraFocus;
        camera.update();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
