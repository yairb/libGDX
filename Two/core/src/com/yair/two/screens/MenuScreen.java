package com.yair.two.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.yair.two.consts.GameConstants;
import com.yair.two.helpers.AssetLoader;

/**
 * Created by yairbarak on 22/09/2016.
 */
public class MenuScreen implements Screen {
    private Game game;

    private OrthographicCamera cam;
    private SpriteBatch batcher;

    private Rectangle startButton;

    private float titleWidth;

    public MenuScreen(Game game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        int buttonSize = 100;

        startButton = new Rectangle((GameConstants.SCREEN_WIDTH - buttonSize) / 2,
                GameConstants.SCREEN_HEIGHT / 2, buttonSize, buttonSize);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(AssetLoader.title, "TWO CARS");
        titleWidth = layout.width;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(GameConstants.MAIN_COLOR.r,
                GameConstants.MAIN_COLOR.g, GameConstants.MAIN_COLOR.b, GameConstants.MAIN_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.enableBlending();
        // title
        AssetLoader.title.draw(batcher, "TWO CARS", (GameConstants.SCREEN_WIDTH - titleWidth) / 2 - 1, 40);

        batcher.draw(AssetLoader.play, startButton.getX(), startButton.getY(), startButton.getWidth(), startButton.getHeight());
        batcher.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPos = cam.unproject(touchPos);

            if (startButton.contains(touchPos.x, touchPos.y)){
                game.setScreen(new GameScreen(game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
