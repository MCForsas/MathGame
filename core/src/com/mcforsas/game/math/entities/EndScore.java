package com.mcforsas.game.math.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.AssetLoader;
import com.mcforsas.game.math.engine.Game;
import com.mcforsas.game.math.levels.Level;
import com.mcforsas.game.math.levels.LevelStart;

import java.text.DecimalFormat;

/*
 * Created by MCForsas on 3/15/2019
 * Replace this text by description, of what this code is for please,
 * you will know nothing about this code after you close the ide.
 */
public class EndScore extends Entitie{
    private double score;
    private DifficultyTypes difficulty;
    private TextRenderer renderer;
    private ButtonAction restart;
    private int time;

    public EndScore(Level level, double score, DifficultyTypes difficulty) {
        super(level);
        this.score = score;
        this.difficulty = difficulty;

        renderer = new TextRenderer(level, Game.WORLD_WIDTH/2, Game.WORLD_HEIGHT/1.5f);
        renderer.setCenterText(true);

        String text = "Time:\n" + new DecimalFormat("0.####").format(score) + "\n";

        restart = new ButtonAction(level, Buttons.RESET, this, 0, Game.WORLD_HEIGHT/4);

        switch (difficulty){
            case EASY:
                text += "Easy";
                break;
            case NORMAL:
                text += "Normal";
                break;
            case HARD:
                text += "Hard";
                break;
            case EXPERT:
                text += "Expert";
        }

        renderer.setText(text);
    }

    @Override
    public void update() {
        super.update();
        if(time >= 40) {
            restart.update();
        }else {
            time++;
        }
    }

    public void buttonClicked(LevelStart levelStart){
        level.purge();
        level.initialize();
        level.getLevelHandler().reset();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);
        renderer.render(spriteBatch);
        restart.render(spriteBatch);
    }

    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
        restart.dispose();
    }
}
