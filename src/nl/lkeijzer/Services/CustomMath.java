package nl.lkeijzer.Services;

import nl.lkeijzer.Objects.Rectangle;

import java.awt.*;

/**
 * Created by lkeij on 2017-05-29.
 */
public final class CustomMath {
    private CustomMath() {
    }

    public static void calcContainer(Rectangle[] rectangles, Dimension container) {
        int maxWidth = 0;
        int maxHeight = 0;
        for (Rectangle rectangle : rectangles) {
            if (rectangle.getHeight() + rectangle.getBottomLeft().y > maxHeight) {
                maxHeight = rectangle.getHeight() + rectangle.getBottomLeft().y;
            }
            if (rectangle.getWidth() + rectangle.getBottomLeft().x > maxWidth) {
                maxWidth = rectangle.getWidth() + rectangle.getBottomLeft().x;
            }
        }
        container.setSize(maxWidth, maxHeight);
    }

    public static double rangeChange(int start, int maxOld, int maxNew, double value) {
        int rangeOld = (maxOld - start);
        if (rangeOld == 0) {
            return start;
        } else {
            int rangeNew = (maxNew - start);
            return (((value - start) * rangeNew) / rangeOld) + start;
        }

    }

    // Assumes start at 0
    public static double rangeChange(int maxOld, int maxNew, double value) {
        return rangeChange(0, maxOld, maxNew, value);
    }
}
