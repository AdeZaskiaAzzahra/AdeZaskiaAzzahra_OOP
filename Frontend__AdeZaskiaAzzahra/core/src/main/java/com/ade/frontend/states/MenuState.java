package com.ade.frontend.states;

import com.ade.frontend.GameManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuState implements GameState {

    private GameStateManager gsm;

    private Stage stage;
    private Skin skin;

    private TextField nameField;
    private TextButton startButton;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        createBasicSkin();
        buildUI();
    }


    private void createBasicSkin() {

        skin = new Skin();

        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        Pixmap white = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        white.setColor(Color.WHITE);
        white.fill();
        skin.add("white", new com.badlogic.gdx.graphics.Texture(white));

        Pixmap gray = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        gray.setColor(Color.GRAY);
        gray.fill();
        skin.add("gray", new com.badlogic.gdx.graphics.Texture(gray));

        Pixmap darkGray = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        darkGray.setColor(Color.DARK_GRAY);
        darkGray.fill();
        skin.add("dark_gray", new com.badlogic.gdx.graphics.Texture(darkGray));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;
        skin.add("default", labelStyle);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.background = skin.newDrawable("dark_gray");
        textFieldStyle.cursor = skin.newDrawable("white");
        textFieldStyle.selection = skin.newDrawable("gray");
        skin.add("default", textFieldStyle);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.up = skin.newDrawable("gray");
        buttonStyle.down = skin.newDrawable("white");
        buttonStyle.over = skin.newDrawable("dark_gray");
        skin.add("default", buttonStyle);
    }


    private void buildUI() {

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label title = new Label("NETLAB JOYRIDE", skin);
        title.setFontScale(2f);

        Label prompt = new Label("Enter Your Name:", skin);

        nameField = new TextField("", skin);
        nameField.setMessageText("Username...");
        nameField.setAlignment(1); // center

        startButton = new TextButton("START GAME", skin);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                String inputName = nameField.getText().trim();
                if (inputName.isEmpty()) {
                    inputName = "Guest";
                }

                GameManager.getInstance().registerPlayer(inputName);

                gsm.set(new PlayingState(gsm));
            }
        });

        table.add(title).padBottom(40);
        table.row();

        table.add(prompt).padBottom(10);
        table.row();

        table.add(nameField).width(300).height(40).padBottom(20);
        table.row();

        table.add(startButton).width(200).height(50);
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        ScreenUtils.clear(Color.BLACK);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
