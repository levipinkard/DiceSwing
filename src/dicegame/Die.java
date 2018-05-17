package dicegame;

import java.security.SecureRandom;

public class Die {
    //Instance variables for current side, and total number of sides
    private int sides;
    private int currentSide;
    //Random number generator instance
    private SecureRandom generator;
    //Takes in number of possible sides, and a number to be added to the seed
    public Die(int sides, int seedAdd) {
        //Sets sides equal to sides given by method call
        this.sides = sides;
        //Starting side is invalid, prevents wrong data from being given with random class malfunction
        currentSide = 0;
        //Constructs generator, supplies first seed to be current system time in milliseconds
        generator = new SecureRandom();
        //Rolls die immediately to get first currentSide value
        roll(seedAdd);
    }
    //Rolls die, takes in seed offset
    public void roll(int seedAdd) {
        //Sets new seed each roll, adds offset in case of use within 1 millisecond
        generator.setSeed(System.currentTimeMillis() + seedAdd);
        //Generates number within boundary from 1 to sides-1
        currentSide = generator.nextInt(sides) + 1;
    }
    //Gets total number of possible sides
    public int getSides () {
        return sides;
    }
    //Gets current side, after roll
    public int getCurrentSide() {
        return currentSide;
    }
}