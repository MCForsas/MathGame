package com.mcforsas.game.math.entities;

/*
 * com.mcforsas.games.math.Utils by root created on 19.2.13
 * Holds game objects, handles calculations
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcforsas.game.math.engine.Game;
import com.mcforsas.game.math.levels.Level;
import com.mcforsas.game.math.levels.LevelGame;
import com.mcforsas.game.math.utils.Eval;
import com.mcforsas.game.math.utils.Utilities;

import java.util.Stack;

public class Controller extends Entitie{
    private String question;
    private Eval eval;
    private DifficultyTypes difficulty;
    private String answer;
    private TextRenderer textRenderer;
    private TextRenderer score;
    private boolean isInitialized = false;
    LevelGame levelGame;

    private double deltaTime = 0;
//    private BitmapFont font

    private Stack<Button> button = new Stack<Button>();

    private float x = 0;
    private float y = 0;

    //private GlyphLayout layout = new GlyphLayout(); //dont do this every frame! Store it as member

    public Controller(Level level, DifficultyTypes difficulty) {
        super(level);
        this.levelGame = (LevelGame) level;
        this.difficulty = difficulty;
        textRenderer = new TextRenderer(this.level, Game.WORLD_WIDTH/2f, Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/10f);
        textRenderer.setMaxScale(0.2f);
//        score = new TextRenderer(this.level, Game.WORLD_WIDTH/2f, Game.WORLD_HEIGHT - Game.WORLD_HEIGHT/10f);
//        score.setCenterText(false);
    }

    @Override
    public void initialize() {
        isInitialized = false;
        textRenderer.setFont(Game.getAssetLoader().getFont("fntGoodTimes"));
        textRenderer.initialize();

        eval = new Eval(difficulty);

        setQuestion(eval.getCalculation());

        int corectAnswer = Utilities.irandomRange(0,3);
        int usedAnswers = 0;

        for(int i = 0; i < 4; i++){
            if(corectAnswer != i) {
                button.add(new Button(level, this, 0, Game.WORLD_HEIGHT - Game.WORLD_HEIGHT / 5 * (i + 2), eval.getFakeAnswer(usedAnswers)));
                usedAnswers++;
            }else{
                button.add(new Button(level, this, 0, Game.WORLD_HEIGHT - Game.WORLD_HEIGHT / 5 * (i + 2), eval.getAnswer()));
                button.lastElement().setCorrect(true);
            }
        }

        for (Button b: button) {
            b.initialize();
        }

        isInitialized = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        //font.setColor(Color.CYAN);
        //font.draw(spriteBatch, question, x ,Game.WORLD_HEIGHT);
        if(isInitialized) {
            textRenderer.render(spriteBatch);

            for (Button b : button) {
                b.render(spriteBatch);
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();

        textRenderer.dispose();

        for (Button b: button) {
            b.dispose();
        }
    }

    @Override
    public void update() {
        boolean touch = level.getLevelHandler().isTouched();

        for (Button b : button) {
         b.update();
        }

    }

    protected void buttonClicked(Button button){
        levelGame.onClick(button.isCorrect());
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
    private void setQuestion(@org.jetbrains.annotations.NotNull String question) {
        this.question = question;

        textRenderer.setText(question);
    }

    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }
}