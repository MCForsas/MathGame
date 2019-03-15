package com.mcforsas.game.math.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.AssetLoader;
import com.mcforsas.game.math.engine.LevelHandler;
import com.mcforsas.game.math.entities.Entitie;

import java.util.Vector;

public abstract class Level {
    protected int ID = 0;
    protected Vector<Entitie> entities = new Vector<Entitie>();
    protected LevelHandler levelHandler;
    protected boolean isInitialized = false;
    protected boolean pause = false;


    public Level(LevelHandler levelHandler){
        this.levelHandler = levelHandler;
    }

    public void initialize(){
        for(Entitie entitie : entities){
            entitie.initialize();
        }

        this.isInitialized = true;
    }

    public void addEntitie(Entitie entitie){
        entities.add(entitie);
    }

    public void update(){
        if(pause){
            return;
        }

        for(Entitie entitie : entities){
            entitie.update();
        }
    }

    public void render(SpriteBatch spriteBatch){
        for(Entitie entitie : entities){
            entitie.render(spriteBatch);
        }
    }

    public void dispose(){
        for(Entitie entitie : entities){
            entitie.dispose();
        }
    }

    public void purge(){
        entities.removeAllElements();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }

    public void setLevelHandler(LevelHandler levelHandler) {
        this.levelHandler = levelHandler;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }
}
