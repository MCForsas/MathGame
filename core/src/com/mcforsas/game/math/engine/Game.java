package com.mcforsas.game.math.engine;

/*
 * com.mcforsas.games.math.Engine.Game by root created on 19.2.13
 * Runs the game. Holds all game objects and utilieties.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mcforsas.game.math.entities.DifficultyTypes;
import com.mcforsas.game.math.levels.LevelGame;

public class Game extends ApplicationAdapter implements InputProcessor {
    //Game world height and width, which is used for on screen coordinates.
    public final static int WORLD_WIDTH = 90;
    public final static int WORLD_HEIGHT = 160;

    private SpriteBatch batch;
    private Renderer renderer;
    private LevelHandler levelHandler;
    public static AssetLoader assetLoader;

    @Override
    public void create () {
        Gdx.input.setInputProcessor(this);
        assetLoader = new AssetLoader();

        //Load all assets

        for(int i = 0; i < 10; i++) {
            assetLoader.loadTexture("spr" + i, "number_" + i + ".png");
        }

        assetLoader.loadTexture("sprAdd","number_add.png");
        assetLoader.loadTexture("sprSubtract","number_subtract.png");
        assetLoader.loadTexture("sprDivide","number_divide.png");
        assetLoader.loadTexture("sprMultiply","number_multiply.png");

        assetLoader.loadTexture("sprMagenta", "magenta.png");
        assetLoader.loadTexture("sprGreen", "green.png");

        assetLoader.loadFont("fntClean","clean_font.fnt");
        assetLoader.loadFont("fntGoodTimes","good_times_128.fnt");

        //Loaded assets

        batch = new SpriteBatch();
        renderer = new Renderer(this);

        levelHandler = new LevelHandler(this);

        LevelGame levelGame = new LevelGame(levelHandler, DifficultyTypes.EASY);

        levelHandler.addLevel(levelGame);
        levelHandler.setCurrentLevel(levelGame);
    }

    @Override
    public void render () {
        renderer.render(batch, levelHandler);
        levelHandler.update();
    }

    @Override
    public void dispose(){
        renderer.dispose(levelHandler);
    }

    @Override
    public void resize(int width, int height){
        renderer.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
        levelHandler.setPaused(true);
    }

    @Override
    public void resume() {
        super.resume();
        levelHandler.setPaused(false);
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
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //Gdx.app.log("Mouse Event","Click at " + screenX + "," + screenY);
        Vector3 worldCoordinates = renderer.getCamera().unproject(new Vector3(screenX,screenY,0));
        //Gdx.app.log("Mouse Event","Projected at " + worldCoordinates.x + "," + worldCoordinates.y);
        levelHandler.touchDown(worldCoordinates.x, worldCoordinates.y);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //Gdx.app.log("Mouse Event","Click at " + screenX + "," + screenY);
        Vector3 worldCoordinates = renderer.getCamera().unproject(new Vector3(screenX,screenY,0));
        //Gdx.app.log("Mouse Event","Projected at " + worldCoordinates.x + "," + worldCoordinates.y);
        levelHandler.touchUp(worldCoordinates.x, worldCoordinates.y);
        return true;
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
    public boolean scrolled(int amount) {
        return false;
    }

    public static AssetLoader getAssetLoader() {
        return assetLoader;
    }

    public static void setAssetLoader(AssetLoader al) {
       assetLoader = al;
    }
}
