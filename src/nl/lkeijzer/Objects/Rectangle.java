package nl.lkeijzer.Objects;

import java.awt.*;
import java.util.Random;

/**
 * An instance of a rectangle.
 *
 * @author Martijn Noordhof, Laurence Keijzer, Tom Peters, Moc Nguyen, Martin Cekodhima
 * 07-05-2017
 */
public class Rectangle implements Comparable<Rectangle> {
    private int height;
    private int width;
    private Point bottomLeft;
    private boolean rotated;
    private int INDEX;
    private Color mColor = new Color(0, 0, 0);

    public Rectangle(int width, int height, int index) {
        this(width, height, null, index);
    }

    public Rectangle(int width, int height, Point bottomLeft, int index) {
        this.height = height;
        this.width = width;
        this.bottomLeft = bottomLeft;
        this.INDEX = index;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the bottom left corner of the rectangle.
     *
     * @param p the point to set.
     */
    public void setBottomLeft(Point p) {
        this.bottomLeft = p;
    }

    /**
     * Gets the bottom left corner of the rectangle.
     *
     * @return the bottom left corner of the rectangle
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * Sets whether or not the rectangle should be rotated.
     *
     * @param rotated whether or not hte rectangle should be rotated
     */
    public void setRotated(boolean rotated) {
        this.rotated = rotated;
    }

    /**
     * Gets whether or not the rectangle is rotated.
     *
     * @return whether or not the rectangle is rotated.
     */
    public boolean isRotated() {
        return this.rotated;
    }

    /**
     * Returns the index in the original array.
     *
     * @return the index in the original array
     */
    public int getIndex() {
        return this.INDEX;
    }

    /**
     * Finds out if {@code r} and {@code this} overlap.
     * @param r the rectangle to check
     * @return whether or not the 2 rectangles have overlap
     * @throws NullPointerException when {@code this.bottomLeft == null}
     */
    public boolean overlap(Rectangle r) {
        Point thisBottomLeft = this.getBottomLeft();
        if (thisBottomLeft == null) {
            throw new NullPointerException("Make sure this rectangle has a bottomleft already assigned!");
        }
        int thisLeft = (int)thisBottomLeft.getX();
        int thisBottom = (int)thisBottomLeft.getY();
        Point rBottomLeft = r.getBottomLeft();
        if (rBottomLeft == null) {
            throw new NullPointerException("Make sure this rectangle has a bottomleft already assigned!");
        }
        int rLeft = (int)rBottomLeft.getX();
        int rBottom = (int)rBottomLeft.getY();
        int thisWidth = this.width;
        int thisHeight = this.height;
        int rWidth = r.getWidth();
        int rHeight = r.getHeight();
        int thisRight = thisLeft + thisWidth;
        int thisTop = thisBottom + thisHeight;
        int rRight = rLeft + rWidth;
        int rTop = rBottom + rHeight;

        return !(rLeft > thisRight || rBottom > thisTop || thisLeft > rRight || thisBottom > rTop);
    }

    public void setColor(Color color) {
        mColor = color;
    }

    public void setColor() {
        Random r = new Random();
        mColor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 150);
    }

    public Color getColor() {
        return mColor;
    }

    @Override
    public int compareTo(Rectangle o) {
        return Integer.compare(this.INDEX, o.getIndex());
    }
}