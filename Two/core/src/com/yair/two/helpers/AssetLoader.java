package com.yair.two.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by yairbarak on 18/09/2016.
 */
public class AssetLoader {
    // game textures
    public static Texture leftCar;
    public static Texture rightCar;
    public static Texture obstacle;
    // game over textures
    public static Texture restart;
    public static Texture back;
    // menu textures
    public static Texture play;
    // fonts
    public static BitmapFont font, shadow, title, klee;
    // preferences
    public static Preferences prefs;
    public static void load() {
        leftCar = new Texture(Gdx.files.internal("gfx/left.png"));
        rightCar = new Texture(Gdx.files.internal("gfx/right.png"));
        obstacle = new Texture(Gdx.files.internal("gfx/obstacle.png"));

        restart = new Texture(Gdx.files.internal("gfx/reload.png"));
        back = new Texture(Gdx.files.internal("gfx/back.png"));

        play = new Texture(Gdx.files.internal("gfx/play.png"));

        float fontSize = .60f;
        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getData().setScale(fontSize, -fontSize);
        shadow = new BitmapFont(Gdx.files.internal("fonts/shadow.fnt"));
        shadow.getData().setScale(fontSize, -fontSize);

        klee = new BitmapFont(Gdx.files.internal("fonts/klee.fnt"));
        klee.getData().setScale(fontSize, -fontSize);

        title = new BitmapFont(Gdx.files.internal("fonts/title.fnt"));
        title.getData().setScale(1.5f, -1.5f);

        prefs = Gdx.app.getPreferences("TwoCars");
        // Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        leftCar.dispose();
        rightCar.dispose();

        play.dispose();
        back.dispose();
        restart.dispose();

        font.dispose();
        shadow.dispose();
        title.dispose();
        klee.dispose();
    }
}
