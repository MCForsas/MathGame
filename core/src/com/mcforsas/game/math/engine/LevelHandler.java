package com.mcforsas.game.math.engine;

/*
 * com.mcforsas.games.math.Engine.LevelHandler by root created on 19.2.13
 * Handles levels, runs them
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.levels.Level;

import java.util.Vector;

public class LevelHandler {
    private boolean paused = false;
    private boolean isTouched = false;
    private float touchX, touchY;
    private AssetLoader assetLoader;

    private Vector<Level> levels = new Vector<Level>();
    private int levelCount = 0;

    private Game game;
    private Level currentLevel;


    public LevelHandler(Game game){
        this.game = game;
        this.assetLoader = game.getAssetLoader();
    }

    public int addLevel(Level level){
        levels.add(levelCount,level);
        level.setID(levelCount);
        levelCount++;
        return levelCount - 1;
    }

    public void update(){
        if(!paused && currentLevel.isInitialized()){
            currentLevel.update();
        }

        if(isTouched){
            isTouched = false;
        }
    }

    public void render(SpriteBatch spriteBatch){

        if(currentLevel.isInitialized()){
            currentLevel.render(spriteBatch);
        }
    }

    public void dispose(){
        currentLevel.dispose();
    }

    public void touchDown(float x, float y){
        isTouched = true;
        touchX = x;
        touchY = y;
    }

    public void touchUp(float x, float y){
        isTouched = false;
        touchX = x;
        touchY = y;
    }

    public void initializeLevel(Level level){
        level.initialize();
    }

    public void initializeLevel(int level){
        levels.get(level).initialize();
    }

    public int getLevelCount() {
        return levelCount;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
        currentLevel.initialize();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isTouched() {
        return isTouched;
    }

    public float getTouchX() {
        return touchX;
    }

    public float getTouchY() {
        return touchY;
    }

    public AssetLoader getAssetLoader() {
        return assetLoader;
    }

    public void setAssetLoader(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
    }

    public void reset(){
        setCurrentLevel(levels.firstElement());
    }
}
