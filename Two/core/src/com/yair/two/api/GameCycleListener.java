package com.yair.two.api;

/**
 * Created by yair on 9/21/16.
 */
public interface GameCycleListener {
    public void gamePause();
    public void gameOver(int score);
}
