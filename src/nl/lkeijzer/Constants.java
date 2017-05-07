package nl.lkeijzer;

import java.awt.*;

/**
 * Created by Laurence on 2017-05-05.
 */
public abstract class Constants {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static final double SCREEN_WIDTH = screenSize.getWidth();
    public static final double SCREEN_HEIGHT = screenSize.getHeight();
}
