package com.yair.two.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.yair.two.consts.GameConstants;

/**
 * Created by yairbarak on 18/09/2016.
 */
public class Obstacle {
    // Is needed to know in which direction to move the obstacle
    private Direction direction;
    // to check if collided
    private Rectangle rectangle;
    // getter - to draw the obstacle
    public Rectangle getRectangle() {
        return rectangle;
    }

    public Obstacle(Direction direction) {
        this.direction = direction;
        int size = GameConstants.SCREEN_HEIGHT / 8;
        // decide randomly if in which path to put the new obstacle
        boolean up = false;
        if (MathUtils.random(0, 30) < 15) {
            up = true;
        }
        // set position
        if (direction == Direction.LEFT) {
            this.rectangle = new Rectangle(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT / 2
                    + size / 2, size, size);
        } else {
            this.rectangle = new Rectangle(0, size / 2, size, size);
        }

        if (!up) {
            this.rectangle.setY(this.rectangle.getY() + GameConstants.SCREEN_HEIGHT / 4);
        }
    }

    // returns true if the obstacle is over Screen's edge, so we can remove it
    public boolean move(float pixels){
        if (direction == Direction.LEFT) {
            rectangle.setX(rectangle.getX() - pixels);
            if (rectangle.getX() + rectangle.getWidth() < 0) {
                return true;
            }
        } else {
            rectangle.setX(rectangle.getX() + pixels);
            if (rectangle.getX() > GameConstants.SCREEN_WIDTH) {
                return true;
            }
        }

        return false;
    }
}
