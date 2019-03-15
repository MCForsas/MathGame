package com.mcforsas.game.math.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.Game;
import com.mcforsas.game.math.engine.LevelHandler;
import com.mcforsas.game.math.entities.ButtonAction;
import com.mcforsas.game.math.entities.Buttons;
import com.mcforsas.game.math.entities.DifficultyTypes;

/*
 * Created by MCForsas on 3/15/2019
 * Replace this text by description, of what this code is for please,
 * you will know nothing about this code after you close the ide.
 */
public class LevelStart extends Level{

    private LevelGame levelGame;
    private ButtonAction buttonActionEasy;
    private ButtonAction buttonActionNormal;
    private ButtonAction buttonActionHard;
    private ButtonAction buttonActionExpert;


    public LevelStart(LevelHandler levelHandler){
        super(levelHandler);
        buttonActionEasy = new ButtonAction(this, Buttons.EASY, this, 0,Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/4);
        buttonActionNormal = new ButtonAction(this, Buttons.NORMAL, this, 0,Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/4*2);
        buttonActionHard = new ButtonAction(this, Buttons.HARD, this, 0, Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/4*3);
        buttonActionExpert = new ButtonAction(this, Buttons.EXPERT, this, 0, Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/4*4);

        addEntitie(buttonActionEasy);
        addEntitie(buttonActionNormal);
        addEntitie(buttonActionHard);
        addEntitie(buttonActionExpert);
    }

    public void buttonClicked(Buttons button){
        DifficultyTypes df = DifficultyTypes.EASY;
        switch (button) {
            case EASY:
                df = DifficultyTypes.EASY;
                break;
            case NORMAL:
                df = DifficultyTypes.NORMAL;
                break;
            case HARD:
                df = DifficultyTypes.HARD;
                break;
            case EXPERT:
                df = DifficultyTypes.EXPERT;
                break;
        }

        this.levelGame = new LevelGame(levelHandler, df);

        levelHandler.addLevel(levelGame);
        levelHandler.setCurrentLevel(levelGame);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
