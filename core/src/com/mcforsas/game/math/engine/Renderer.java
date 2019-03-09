package com.mcforsas.game.math.engine;

/*
 * com.mcforsas.games.math.Utils by root created on 19.2.13
 * Renders level and HUD
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Stack;

public class Renderer {
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stack<Renderable> renderables;
    private Game game;

    Renderer(Game game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(Game.WORLD_WIDTH, Game.WORLD_HEIGHT,camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
        renderables = new Stack<Renderable>();
    }

    public void render(SpriteBatch sb, LevelHandler levelHandler){
        camera.update();

        //Set background color
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        for (Renderable r: renderables) {
            r.render(sb);
        }

        levelHandler.render(sb);
        sb.end();

    }

    public void dispose(LevelHandler levelHandler){
        levelHandler.dispose();
    }

    public void resize(int width, int height){
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    public void addRenderable(Renderable renderable){
        renderables.push(renderable);
    }

    public Stack getRenderables(){
        return renderables;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
