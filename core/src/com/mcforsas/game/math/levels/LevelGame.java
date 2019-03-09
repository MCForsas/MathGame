package com.mcforsas.game.math.levels;

import com.mcforsas.game.math.engine.LevelHandler;
import com.mcforsas.game.math.entities.Controller;
import com.mcforsas.game.math.entities.DifficultyTypes;

public class LevelGame extends Level{

    public LevelGame(LevelHandler levelHandler) {
        super(levelHandler);
        addEntitie(new Controller(this, DifficultyTypes.EASY));
    }

    public void reset(){

    }
}
