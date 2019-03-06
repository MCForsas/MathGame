package com.mcforsas.game.math.entities;

/*
 * com.mcforsas.games.math.Utils by root created on 19.2.13
 * Holds game objects, handles calculations
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.Game;
import com.mcforsas.game.math.levels.Level;
import com.mcforsas.game.math.utils.Eval;

public class Controller extends Entitie{
    String question;
    Eval eval;
    DifficultyTypes difficulty;
    String answer;
    BitmapFont font;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    float x = 0;
    float y = 0;

    GlyphLayout layout = new GlyphLayout(); //dont do this every frame! Store it as member

    public Controller(Level level, DifficultyTypes difficulty) {
        super(level);
        this.difficulty = difficulty;
    }

    @Override
    public void initialize() {
        font = level.getLevelHandler().getAssetLoader().getFont("fntGoodTimes");
        eval = new Eval();
        setQuestion(eval.generateCalculationByDifficulty(difficulty));
        setAnswer(String.valueOf(eval.eval(question)));

        button0 = new Button(level,this, 0,Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/5*2,false);
        button1 = new Button(level,this, 0,Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/5*3,false);
        button2 = new Button(level,this, 0,Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/5*4,false);
        button3 = new Button(level,this, 0,Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/5*5,false);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        font.setColor(Color.CYAN);
        font.draw(spriteBatch, question, x ,Game.WORLD_HEIGHT);

        button0.render(spriteBatch);
        button1.render(spriteBatch);
        button2.render(spriteBatch);
        button3.render(spriteBatch);
    }

    @Override
    public void dispose() {
        super.dispose();
        button0.dispose();
        button1.dispose();
        button2.dispose();
        button3.dispose();
    }

    @Override
    public void update() {
        boolean touch = level.getLevelHandler().isTouched();

        setQuestion(eval.generateCalculationByDifficulty(difficulty));

        if(touch){
//            y = level.getLevelHandler().getTouchY();
//            x = level.getLevelHandler().getTouchX();
            setQuestion(question);
        }

        button0.update();
        button1.update();
        button2.update();
        button3.update();
    }

    public void buttonClicked(Button button){
        if(button.isCorrect()){
            generateNewQuestion();
        }
    }

    private void generateNewQuestion(){
        setQuestion(eval.generateCalculationByDifficulty(DifficultyTypes.EASY));
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    //Sets the question and readjusts font size
    public void setQuestion(String question) {
        this.question = question;
        font.getData().setScale(1f/question.length());
        layout.setText(font, this.question);

        x = Game.WORLD_WIDTH/2 - layout.width/2;
    }
}