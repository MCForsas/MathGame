package com.mcforsas.game.math.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.LevelHandler;
import com.mcforsas.game.math.entities.Controller;
import com.mcforsas.game.math.entities.DifficultyTypes;
import com.mcforsas.game.math.entities.EndScore;

public class LevelGame extends Level{

    private double deltaTime = 0;
    private DifficultyTypes difficulty;

    private final float DIFF_EASY = 1f;
    private final float DIFF_NORMAL = 1.5f;
    private final float DIFF_HARD = 2f;
    private final float DIFF_EXPERT = 2.5f;

    private final float PUNISH = 10;
    private double time = 0;

    private int completed = 0;
    private final int TASK_SIZE = 5;
    private boolean gameFinished = false;

    private Controller controller;

    private EndScore endScore;

    public LevelGame(LevelHandler levelHandler, DifficultyTypes difficulty) {
        super(levelHandler);
        this.difficulty = difficulty;
    }

    @Override
    public void initialize() {
        this.controller = new Controller(this, difficulty);
        addEntitie(controller);
        deltaTime = 0;
        super.initialize();
    }

    public void onClick(boolean isCorrect){
        if(isCorrect) {
            correctClick();
        }else{
            wrongClick();
        }
    }

    private void wrongClick(){
        switch (difficulty){
            case EASY:
                deltaTime += PUNISH * DIFF_EASY;
                break;
            case NORMAL:
                deltaTime += PUNISH * DIFF_NORMAL;
                break;
            case HARD:
                deltaTime += PUNISH * DIFF_HARD;
                break;
            case EXPERT:
                deltaTime += PUNISH * DIFF_EXPERT;
                break;
        }
    }

    private void correctClick(){
        time += deltaTime;
        if(TASK_SIZE > completed + 1){
            completed++;
            //Debug
            purge();
            initialize();
        }else{
            end();
        }
    }

    @Override
    public void update() {
        if(!gameFinished) {
            super.update();
            deltaTime += 0.01;
            controller.setDeltaTime(deltaTime);
        }
    }

    public void render(SpriteBatch sb){
        if(!gameFinished)
            super.render(sb);
        if(endScore != null){
            endScore.render(sb);
        }
    }

    private void end(){
       gameFinished = true;
       endScore = new EndScore(this, time, difficulty);
    }

    @Override
    public void dispose() {
        super.dispose();
        if(endScore != null){
            endScore.dispose();
        }
    }

}
