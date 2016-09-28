package com.yair.two.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.yair.two.api.GameCycleListener;
import com.yair.two.helpers.GameRenderer;
import com.yair.two.helpers.GameWorld;
import com.yair.two.helpers.InputHandler;

/**
 * Created by yairbarak on 18/09/2016.
 */
public class GameScreen implements Screen, GameCycleListener {
    private GameWorld world;
    private GameRenderer renderer;
    private Game game;

    public GameScreen(Game game) {
        this.game = game;

        world = new GameWorld(this); // initialize world
        renderer = new GameRenderer(world); // initialize renderer

        Gdx.input.setInputProcessor(new InputHandler(world, renderer.getCam()));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
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
        renderer.dispose();
    }

    // gameCycle methods
    @Override
    public void gameOver(int score) {
        game.setScreen(new GameOverScreen(game, score));
    }

    @Override
    public void gamePause() {

    }
}
