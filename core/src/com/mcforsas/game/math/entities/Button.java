package com.mcforsas.game.math.entities;/*
 * com.mcforsas.games.math.Entities by root created on 19.2.11
 * Can be pressed and executes some action
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.AssetLoader;
import com.mcforsas.game.math.engine.Game;
import com.mcforsas.game.math.levels.Level;
import com.mcforsas.game.math.levels.LevelGame;
import com.mcforsas.game.math.utils.Utilities;

import java.text.DecimalFormat;
//import com.mcforsas.game.math.utils.Utilities;

public class Button extends Entitie{

    private float x, y;
    private boolean isCorrect;
    private Controller actionListener;
    private TextRenderer textRenderer;
    double answer;
    int height = Game.WORLD_HEIGHT/5;
    int width = Game.WORLD_WIDTH;

    public Button(Level level, Controller controller, float x, float y, double answer) {
        super(level);

        this.x = x;
        this.y = y;
        this.answer = answer;
        this.actionListener = controller;

        textRenderer = new TextRenderer(level, x+width/2, y+height/2);
        textRenderer.setMaxScale(0.2f);

        actionListener = controller;

        sprite = new Sprite(Game.getAssetLoader().getTexture("sprGreen"));
        sprite.setSize(Game.WORLD_WIDTH, Game.WORLD_HEIGHT/5);
        sprite.setPosition(x,y);
    }

    @Override
    public void initialize() {
        super.initialize();

        String df = new DecimalFormat("0.###").format(this.answer);

        textRenderer.setText(df);
    }

    @Override
    public void update() {
        super.update();

        if(level.getLevelHandler().isTouched()){
            float touchX = level.getLevelHandler().getTouchX();
            float touchY = level.getLevelHandler().getTouchY();
//
            if(Utilities.isInRange(touchX, this.x, this.x + this.width) && Utilities.isInRange(touchY, this.y, this.y + this.height)){
                actionListener.buttonClicked(this);
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        //super.render(spriteBatch);
        textRenderer.render(spriteBatch);
    }

    @Override
    public void dispose() {
        super.dispose();
        textRenderer.dispose();
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
