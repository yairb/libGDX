package com.yair.two.helpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.yair.two.api.GameCycleListener;
import com.yair.two.consts.GameConstants;
import com.yair.two.objects.Direction;
import com.yair.two.objects.Obstacle;

/**
 * Created by yairbarak on 18/09/2016.
 */
public class GameWorld {
    public enum GameState {PLAY, GAME_OVER}
    private GameState state = GameState.PLAY;
    // game objects
    private Rectangle leftCar;
    private Rectangle rightCar;
    private Array<Obstacle> obstacles = new Array<Obstacle>();
    // helpers
    private long lastObstacleSpawnTime;
    private long spawnAfterTime = GameConstants.INITIAL_SPAWN_AFTER_TIME;
    private int score = 0;
    private GameCycleListener gameCyclelistener;
    // sizes and positions
    private Direction leftCarPosition = Direction.DOWN;
    private Direction rightCarPosition = Direction.UP;
    private float pathHeight = GameConstants.SCREEN_HEIGHT / 4;
    // getters
    public Rectangle getLeftCar() {
        return leftCar;
    }
    public Rectangle getRightCar() {
        return rightCar;
    }
    public Array<Obstacle> getObstacles() {
        return obstacles;
    }
    public GameState getState() {
        return state;
    }
    public int getScore() {
        return score;
    }

    public GameWorld(GameCycleListener gameCyclelistener){
        this.gameCyclelistener = gameCyclelistener;
        // the car images we use are 230x122 px
        float ratio = 230f / 122f;
        float carHeight = GameConstants.SCREEN_HEIGHT / 8;
        float carWidth = carHeight * ratio;

        int screenOffset = 10;
        leftCar = new Rectangle(screenOffset, GameConstants.SCREEN_HEIGHT - carHeight - GameConstants.SCREEN_HEIGHT / 16,
                carWidth, carHeight);
        rightCar = new Rectangle(GameConstants.SCREEN_WIDTH - carWidth - screenOffset, GameConstants.SCREEN_HEIGHT / 16,
                carWidth, carHeight);
    }
    public void update(float delta) {
        if (state != GameState.GAME_OVER) {
            float move = 5;
            for (Obstacle o : obstacles) {
                if (o.move(move)) {
                    // obstacle reach screen edge
                    obstacles.removeValue(o, false);
                    score++;
                    if (score % 10 == 0) {
                        // Harder!
                        spawnAfterTime -= 50000;
                        // Harder!
                        move += 0.1f;
                    }
                } else {
                    // we don't want to go to hard on the user.
                    // so Game_Over will be only if the close EDGE of obstacle is in car's rect
                    if (leftCar.contains(o.getRectangle().getX(), o.getRectangle().getY()) ||
                            rightCar.contains(o.getRectangle().getX() + o.getRectangle().getWidth(), o.getRectangle().getY())){
                        state = GameState.GAME_OVER;
                        // notify Screen that the game overed
                        gameCyclelistener.gameOver(score);
                    }
                }
            }
            // Check if it's time for new obstacles
            // We divide the nanoTimes in 100 - otherwise it's too much for long
            if ((TimeUtils.nanoTime() - lastObstacleSpawnTime) / 100 > spawnAfterTime) {
                spawnObstacles();
            }
        }
    }

    private void spawnObstacles(){
        obstacles.add(new Obstacle(Direction.LEFT));
        obstacles.add(new Obstacle(Direction.RIGHT));

        lastObstacleSpawnTime = TimeUtils.nanoTime();
    }

    public void touched(Direction direction) {
        if (direction == Direction.LEFT) {
            if (leftCarPosition == Direction.UP) {
                leftCar.setY(leftCar.getY() + pathHeight);
                leftCarPosition = Direction.DOWN;
            } else {
                leftCar.setY(leftCar.getY() - pathHeight);
                leftCarPosition = Direction.UP;
            }
        } else {
            if (rightCarPosition == Direction.UP) {
                rightCar.setY(rightCar.getY() + pathHeight);
                rightCarPosition = Direction.DOWN;
            } else {
                rightCar.setY(rightCar.getY() - pathHeight);
                rightCarPosition = Direction.UP;
            }
        }
    }
}
