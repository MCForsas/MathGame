package com.mcforsas.game.math.utils;
/*
 * com.mcforsas.games.math.Utils by root created on 19.2.13
 * Converts strings to mathematical functions
 */

import com.mcforsas.game.math.entities.DifficultyTypes;

public class Eval {
    private String function = "";
    private double answer = 0.0;
    private static final int MAX_CALCULATIONS = 10;

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public String generateCalculationByDifficulty(DifficultyTypes difficulty){

        String calculation = "0";

        switch(difficulty){
            case EASY:{
                calculation = generateCalculation(10,5,10);
                break;
            }

            case NORMAL:{
                calculation = generateCalculation(10,20,10);
                break;
            }

            case HARD:{
                calculation = generateCalculation(10,50,20);
                break;
            }

            case EXPERT:{
                calculation = generateCalculation(15,80,30);
                break;
            }
        }

        return calculation;
    }

    private String generateCalculation(int maxNumber, int chanceOfComplexCalculations, int chanceOfAppendedCalculations){
        StringBuilder stringBuilder = new StringBuilder();

        int calculationsCounter = 0;

        do{
            stringBuilder.append(Utilities.irandomRange(1,maxNumber));
            stringBuilder.append(
                    Utilities.pick(
                        Utilities.choose('+','-'),
                        Utilities.choose('*','/'),
                        chanceOfComplexCalculations
                    )
            );
            calculationsCounter++;

        }while ((Utilities.chance(chanceOfAppendedCalculations) || calculationsCounter <= chanceOfAppendedCalculations/MAX_CALCULATIONS) && calculationsCounter <= MAX_CALCULATIONS);

        stringBuilder.append(Utilities.irandomRange(1,maxNumber));

        return stringBuilder.toString();
    }
}
