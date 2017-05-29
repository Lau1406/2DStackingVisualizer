package nl.lkeijzer.Services;

import nl.lkeijzer.Objects.Rectangle;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This object reads the input and sets the corresponding variables of the corresponding PackingSolver class accordingly.
 *
 * @author Martijn Noordhof, Laurence Keijzer, Tom Peters, Moc Nguyen, Martin Cekodhima
 * 4-5-2017
 */

public class InputReader {

    private Rectangle[] mRectangles;
    private boolean mRotationsAllowed = false;
    private boolean mFixedHeight = false;
    private int mHeight = 0;

    public InputReader() {

    }

    /**
     * Reads the input and sets the values of the corresponding PackingSolver accordingly.
     *
     * Returns nothing, the variables of {@code main}  are directly modified instead.
     * @param file the file to read the input from. If input should be read from the commentline, use null.
     */
    public Rectangle[] readInput(File file) {
        Scanner sc;
        try{
            if (file == null) {
                sc = new Scanner(System.in);
            } else {
                sc = new Scanner(file);
            }

            readHeight(sc);
            readRotationsAllowed(sc);
            readRectangleSizes(sc);
            readRectangLocations(sc);
            // Set rectangles in MainFrame
            System.out.println("Done reading input.");
            return mRectangles;

        } catch (FileNotFoundException e) {
            System.err.println("The specified input file was not found");
            e.printStackTrace();
        } catch (InputMismatchException|NegativeArraySizeException e) {
            System.err.println("The input is not in the correct format, don't run the program twice after each other with the same file and please use the following format:\n" +
                    "container height: variant [H]\n" +
                    "rotations allowed: version\n" +
                    "number of rectangles: n\n" +
                    "w1 h1\n" +
                    "...\n" +
                    "wn hn\n" +
                    "where variant is in {free, fixed}, [H] is the height if variant == fixed, version is in {yes, no}, n is a natural number and the amount of rectangles is equal to n!\n");
            e.printStackTrace();
        }
        return null;
    }

    public Rectangle[] getRectangles() {
        return mRectangles;
    }

    /**
     * Reads if the height is fixed and sets the main variable accordingly.
     *
     * @param sc the scanner to be used
     */
    private void readHeight(Scanner sc) {
        //Consume 'container height'
        sc.next();
        sc.next();
        //read if the height is fixed or free and set it accordingly
        String heightFixed = sc.next();
        if (heightFixed.equalsIgnoreCase("fixed")) {
            mFixedHeight = true;
            mHeight = sc.nextInt();
//            mMainFrame.setFixedHeight(true);
//            mMainFrame.setMaxHeight(height);
        } else if (heightFixed.equalsIgnoreCase("free")) {
            mFixedHeight = false;
//            mMainFrame.setFixedHeight(false);
//            mMainFrame.setMaxHeight(Integer.MAX_VALUE);
        }
    }

    /**
     * Reads if rotations are allowed and sets the main variable accordingly.
     *
     * @param sc the scanner to be used
     */
    private void readRotationsAllowed(Scanner sc) {
        //consume 'rotations allowed'
        sc.next();
        sc.next();

        //read if rotations are allowed
        String rotationsAllowed = sc.next();
        if (rotationsAllowed.equalsIgnoreCase("yes")) {
            mRotationsAllowed = true;
        } else if (rotationsAllowed.equalsIgnoreCase("no")) {
            mRotationsAllowed = false;
        }
    }

    /**
     * Reads the rectangles and sets the main variable accordingly.
     *
     * @param sc the scanner to be used
     */
    private void readRectangleSizes(Scanner sc) {
        //consume 'number of rectangles'
        sc.next();
        sc.next();
        sc.next();

        //reads the number of rectangles

        int amountOfRectangles = sc.nextInt();
        mRectangles = new Rectangle[amountOfRectangles];

        for (int i = 0; i < amountOfRectangles; i++) {
            int width = sc.nextInt();
            int height = sc.nextInt();
            mRectangles[i] = new Rectangle(width, height, i);
            mRectangles[i].setColor();
        }
    }

    /**
     * Reads the rectangles, stores them in {@code mRectangles}, stores them in {@code MainFrame} and returns {@code mRectangles}.
     *
     * @param sc the scanner to be used
     */
    private void readRectangLocations(Scanner sc) {
        // Consume 'placement of rectangles'
        sc.next();
        sc.next();
        sc.next();

        // Read all the locations of the rectangles
        for (int i = 0; i < mRectangles.length; i++) {
            if (mRotationsAllowed) {
                sc.next();  // Consume 'yes' or 'no'
            }
            mRectangles[i].setBottomLeft(new Point(sc.nextInt(), sc.nextInt()));
        }
    }

    public boolean isRotationsAllowed() {
        return mRotationsAllowed;
    }

    public boolean isFixedHeight() {
        return mFixedHeight;
    }

    public int getHeight() {
        return mHeight;
    }
}
