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
public class GameOverScreen implements Screen {
    private Game game;
    private int score;

    private OrthographicCamera cam;
    private SpriteBatch batcher;

    private Rectangle backButton;
    private Rectangle restartButton;

    private float gameOverTextWidth;
    private float scoreWidth;
    private float bestScoreWidth;
    private boolean newHighScore = false;

    public GameOverScreen(Game game, int score) {
        this.game = game;
        this.score = score;

        int currentHighScore = AssetLoader.getHighScore();
        if (score > currentHighScore) {
            AssetLoader.setHighScore(score);
            newHighScore = true;
        }

        cam = new OrthographicCamera();
        cam.setToOrtho(true, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);


        int buttonSize = 100;
        int offset = 10;

        backButton = new Rectangle(GameConstants.SCREEN_WIDTH / 2 - (buttonSize + offset),
                3 * GameConstants.SCREEN_HEIGHT / 4, buttonSize, buttonSize);
        restartButton = new Rectangle(GameConstants.SCREEN_WIDTH / 2 + offset,
                3 * GameConstants.SCREEN_HEIGHT / 4, buttonSize, buttonSize);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(AssetLoader.title, "GAME OVER");
        gameOverTextWidth = layout.width;

        layout.setText(AssetLoader.klee, "YOUR SCORE: " + score);
        scoreWidth = layout.width;

        layout.setText(AssetLoader.klee, "HighScore: " + AssetLoader.getHighScore());
        bestScoreWidth = layout.width;
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
        batcher.draw(AssetLoader.back, backButton.getX(), backButton.getY(), backButton.getWidth(), backButton.getHeight());
        batcher.draw(AssetLoader.restart, restartButton.getX(), restartButton.getY(), restartButton.getWidth(), restartButton.getHeight());

        // title
        AssetLoader.title.draw(batcher, "GAME OVER", (GameConstants.SCREEN_WIDTH - gameOverTextWidth) / 2 - 1, 24);

        // score
        AssetLoader.klee.draw(batcher, "YOUR SCORE: " + score, (GameConstants.SCREEN_WIDTH - scoreWidth) / 2, GameConstants.SCREEN_HEIGHT / 4);

        if (AssetLoader.getHighScore() > 0) {
            AssetLoader.klee.draw(batcher, "HighScore: " + AssetLoader.getHighScore() , (GameConstants.SCREEN_WIDTH - bestScoreWidth) / 2, GameConstants.SCREEN_HEIGHT / 2);
        }

        if (newHighScore) {

        }

        batcher.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            if (backButton.contains(touchPos.x, touchPos.y)){
                game.setScreen(new MenuScreen(game));
            } else if (restartButton.contains(touchPos.x, touchPos.y)) {
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
