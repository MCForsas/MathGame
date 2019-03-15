package com.mcforsas.game.math.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.Game;
import com.mcforsas.game.math.levels.Level;

/*
 * Created by MCForsas on 3/15/2019
 * Replace this text by description, of what this code is for please,
 * you will know nothing about this code after you close the ide.
 */
public class ButtonAction extends Entitie{

    private float x, y;
    private TextRenderer textRenderer;
    double answer;
    int height = Game.WORLD_HEIGHT/5;
    int width = Game.WORLD_WIDTH;

    public ButtonAction(Level level) {
        super(level);
        sprite = new Sprite(Game.getAssetLoader().getTexture("sprGreen"));
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void dispose() {
        super.dispose();
        textRenderer.dispose();
    }

    public void render(SpriteBatch sb){
        textRenderer.render(sb);
        super.render(sb);
    }
}
