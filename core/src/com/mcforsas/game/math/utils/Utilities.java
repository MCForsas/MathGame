package com.mcforsas.game.math.utils;

import java.util.Random;

/*
 * A bunch of usefull functions
 * @author MCForsas on 19/01/2019
 */
public final class Utilities {

    /*
     * Clamps a value between max and min
     * @param value
     * @param max
     * @param min
     * @return float clamped value
     */
    public static float clamp(float val, float max, float min){
        if(val > max){
            return max;
        }
        if(val < min){
            return min;
        }
        return val;
    }

    /*
     * Chooses random object from given ones
     * @param Object... objects
     * @return Object
     */
    public static Object choose(Object... objects){
        Random r = new Random();
        return objects[r.nextInt(objects.length)];
    }

    /*
     * Returns random int between 0 and max
     * @return int
     */
    public static int irandom(int max){
        Random r = new Random();
        return r.nextInt(max);
    }

    /*
     * Returns random int between min and max
     * @return int
     */
    public static int irandomRange(int min, int max){
        Random r = new Random();
        return (r.nextInt((max - min) + 1) + min);
    }

    /*
     * Returns true at given chance
     * @param int percentage number between 0 - 100
     * @return boolean chance true chance% of the time
     */
    public static boolean chance(int percentage){
        Random r = new Random();
        return percentage > r.nextInt(100);
    }

    /*
     * Returns first object chance% of the time and second object  100% - chance% of the time
     * @param Object object1
     * @param Object object2
     * @param int percentage number between 0 - 100
     * @return Object chosen object
     */
    public static Object pick(Object object1, Object object2, int percentage){
        return chance(percentage) ? object2 : object1;
    }

    public static boolean flipBoolean(boolean bool){
        return !bool;
    }

    /*
     * returns number between 0 and 1, which is eased in.
     * @param int progress
     * @return eased out
     */

    public static float easeIn(float value){
        float y = (float) Math.pow(Math.sin(5*value/Math.PI),2);
        return y;
    }

    /*
     * simple proportion
     * low - high
     * val - x
     * x = low * val / high
     */
    public static float proportion(float value, float low, float high){
        return (high * value)/low;
    }

    /*
     * Approaches one value to other, by given completion amount (0 - 1);
     * @param float completion completion rating from 0 to 1
     * @param float from start value
     * @param float to end value
     */
    public static float approach(float completion, float from, float to){
        return from + (to - from) * completion;
    }

    /*
     * Checks if value falls in range between two other values
     * @param value value to check
     * @param min min value
     * @param max max value
     * @param return inRange is in range?
     */
    public static boolean isInRange(float value, float min, float max){
        return (value >= min && value <= max);
    }
}