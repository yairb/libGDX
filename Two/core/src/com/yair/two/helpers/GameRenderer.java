package com.yair.two.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.yair.two.consts.GameConstants;
import com.yair.two.objects.Obstacle;

/**
 * Created by yairbarak on 18/09/2016.
 */
public class GameRenderer {
    private OrthographicCamera cam;
    private SpriteBatch batcher;
    private ShapeRenderer shapeRenderer;
    private GameWorld gameWorld;
    private Array<Rectangle> lines;

    public OrthographicCamera getCam() {
        return cam;
    }

    public GameRenderer(GameWorld world){
        this.gameWorld = world;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        // create once the rects to the lines of bg, to save calculations time
        int lineWidth = 6;
        lines = new Array<Rectangle>();
        lines.add(new Rectangle(0, GameConstants.SCREEN_HEIGHT / 2 - lineWidth / 2, GameConstants.SCREEN_WIDTH, lineWidth));
        lines.add(new Rectangle(0, GameConstants.SCREEN_HEIGHT / 4 - lineWidth / 4, GameConstants.SCREEN_WIDTH, lineWidth/2));
        lines.add(new Rectangle(0, 3 * GameConstants.SCREEN_HEIGHT / 4 - lineWidth / 4, GameConstants.SCREEN_WIDTH, lineWidth/2));
    }

    public void render(){
        // clear
        Gdx.gl.glClearColor(GameConstants.MAIN_COLOR.r,
                GameConstants.MAIN_COLOR.g, GameConstants.MAIN_COLOR.b, GameConstants.MAIN_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // create bg lines
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        for (Rectangle line : lines) {
            shapeRenderer.rect(line.getX(), line.getY(), line.getWidth(), line.getHeight());
        }
        shapeRenderer.end();

        // game objects
        batcher.begin();
        batcher.enableBlending();

        // obstacles
        for (Obstacle o : gameWorld.getObstacles()) {
            batcher.draw(AssetLoader.obstacle, o.getRectangle().getX(), o.getRectangle().getY(),
                    o.getRectangle().getWidth(), o.getRectangle().getHeight());
        }

        // left Car
        Rectangle left = gameWorld.getLeftCar();
        batcher.draw(AssetLoader.leftCar, left.getX(), left.getY(), left.getWidth(), left.getHeight());
        // right Car
        Rectangle right = gameWorld.getRightCar();
        batcher.draw(AssetLoader.rightCar, right.getX(), right.getY(), right.getWidth(), right.getHeight());

        // score
        // Draw shadow first
        AssetLoader.shadow.draw(batcher, "" + gameWorld.getScore(), 100, 25);
        // Draw text
        AssetLoader.font.draw(batcher, "" + gameWorld.getScore(), 99, 24);
        batcher.end();

    }

    public void dispose(){
        shapeRenderer.dispose();
        batcher.dispose();
    }
}
