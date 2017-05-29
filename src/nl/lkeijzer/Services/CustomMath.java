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
}
