package com.mcforsas.game.math.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.Game;
import com.mcforsas.game.math.levels.Level;
import com.mcforsas.game.math.levels.LevelStart;
import com.mcforsas.game.math.utils.Utilities;

/*
 * Created by MCForsas on 3/15/2019
 * Replace this text by description, of what this code is for please,
 * you will know nothing about this code after you close the ide.
 */
public class ButtonAction extends Entitie{

    private float x, y;
    private TextRenderer renderer;
    int height = Game.WORLD_HEIGHT/4;
    int width = Game.WORLD_WIDTH;
    private Buttons type;
    private LevelStart levelStart;
    private EndScore endScore;

    public ButtonAction(Level level, Buttons type, LevelStart levelStart, float x, float y) {
        super(level);
//        sprite = new Sprite(Game.getAssetLoader().getTexture("sprGreen"));
//        sprite.setSize(width,height);
//        sprite.setPosition(x,y);

        renderer = new TextRenderer(level, x+width/2, y+height/2);
        renderer.setMaxScale(0.1f);
        renderer.setCenterText(true);

        String txt = "";
        switch (type) {
            case EASY:
                txt = "EASY";
                break;
            case NORMAL:
                txt = "NORMAL";
                break;
            case HARD:
                txt = "HARD";
                break;
            case EXPERT:
                txt = "EXPERT";
                break;
            case RESET:
                break;
        }

        renderer.setText(txt);

        this.type = type;
        this.x = x;
        this.y = y;
        this.levelStart = levelStart;
    }

    public ButtonAction(Level level, Buttons type, EndScore endScore, float x, float y) {
        super(level);
//        sprite = new Sprite(Game.getAssetLoader().getTexture("sprGreen"));
//        sprite.setSize(width,height);
//        sprite.setPosition(x,y);

        renderer = new TextRenderer(level, x+width/2, y+height/2);
        renderer.setMaxScale(0.08f);

        renderer.setText("Play again");

        this.type = type;
        this.x = x;
        this.y = y;
        this.endScore = endScore;
    }

    @Override
    public void update() {
        super.update();
        if(level.getLevelHandler().isTouched()){
            float touchX = level.getLevelHandler().getTouchX();
            float touchY = level.getLevelHandler().getTouchY();

            if(Utilities.isInRange(touchX, this.x, this.x + this.width) && Utilities.isInRange(touchY, this.y, this.y + this.height)){
                if(levelStart != null){
                    levelStart.buttonClicked(this.type);
                }

                if(endScore != null){
                    endScore.buttonClicked(levelStart);
                }
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
    }

    public void render(SpriteBatch sb){
        super.render(sb);
        renderer.render(sb);
    }
}
