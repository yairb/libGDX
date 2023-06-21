package com.yair.two.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.yair.two.consts.GameConstants;
import com.yair.two.objects.Direction;

/**
 * Created by yairbarak on 18/09/2016.
 */
public class InputHandler implements InputProcessor {
    private GameWorld world;
    private Camera camera;

    Vector3 point;

    public InputHandler(GameWorld world, Camera cam) {

        this.world = world;
        this.camera = cam;

        this.point = new Vector3();
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (world.getState() != GameWorld.GameState.GAME_OVER) {
            if (character == 'z') {
                this.world.touched(Direction.LEFT);
                return true;
            } else if (character == 'a') {
                this.world.touched(Direction.RIGHT);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        point.set(screenX, screenY, 0); // Translate to world coordinates.
        point = camera.unproject(point);

        if (world.getState() != GameWorld.GameState.GAME_OVER) {
            if (point.y < GameConstants.SCREEN_HEIGHT / 2) {
                this.world.touched(Direction.RIGHT);
            } else {
                this.world.touched(Direction.LEFT);
            }
        } else {

        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}