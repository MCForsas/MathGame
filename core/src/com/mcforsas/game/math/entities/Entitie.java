package com.mcforsas.game.math.entities;

/*
 * com.mcforsas.games.math.Utils by root created on 19.2.13
 * Entitie class from which all the entitie objects extend. Will be handled by level handler.
 */

import com.mcforsas.game.math.engine.Renderable;
import com.mcforsas.game.math.levels.Level;

public abstract class Entitie extends Renderable {
    protected Level level;

    public Entitie(Level level){
        this.level = level;
    }

    public void initialize(){

    }

    public void update(){

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
