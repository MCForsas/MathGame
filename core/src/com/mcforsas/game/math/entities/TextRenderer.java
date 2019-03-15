package com.mcforsas.game.math.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.Game;
import com.mcforsas.game.math.levels.Level;
import com.mcforsas.game.math.utils.Utilities;

/*
 * Created by MCForsas on 3/6/2019
 * This class renders text, provided font and draw it on the screen
 * you will know nothing about this code after you close the ide.
 */
public class TextRenderer extends Entitie {

    private String text = "";
    private BitmapFont font;
    private float x, y, drawX, drawY;
    private GlyphLayout layout;
    private float maxScale = 100;
    private  float minScale = 0.01f;
    private boolean centerText = true;

    public TextRenderer(Level level, float x, float y) {
        super(level);
        this.x = x;
        this.y = y;

        setFont(Game.getAssetLoader().getFont("fntGoodTimes"));
        layout = new GlyphLayout();
    }

    @Override
    public void initialize() {
        super.initialize();
        this.layout = new GlyphLayout();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if(centerText) {
            centerText();
        }else{
            drawX = x;
            drawY = y;
        }
        super.render(spriteBatch);
        font.setColor(Color.WHITE);
        font.draw(spriteBatch, text, drawX, drawY);
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }

    private void centerText(){
        float scale = 1f/this.text.length();
        scale = Utilities.clamp(scale, minScale, maxScale);

        font.getData().setScale(scale);

        layout.setText(font, text);

        drawX = x - layout.width/2f;
        drawY = y + layout.height/2f;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        centerText();
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getMaxScale() {
        return maxScale;
    }

    public void setMaxScale(float maxScale) {
        this.maxScale = maxScale;
    }

    public float getMinScale() {
        return minScale;
    }

    public void setMinScale(float minScale) {
        this.minScale = minScale;
    }

    public boolean isCenterText() {
        return centerText;
    }

    public void setCenterText(boolean centerText) {
        this.centerText = centerText;
    }
}
