package com.mcforsas.game.math.engine;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * Created by MCForsas on 2/23/2019
 * Can be rendered by renderer
 */
public abstract class Renderable {
    protected Sprite sprite;

    public void render(SpriteBatch spriteBatch){
        if(sprite != null)
            sprite.draw(spriteBatch);
    }

    public void dispose(){
        if(sprite != null)
            sprite.getTexture().dispose();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
