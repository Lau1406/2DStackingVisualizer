package nl.lkeijzer;

import java.awt.*;

/**
 * Created by Laurence on 2017-05-05.
 */
public abstract class Constants {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static final double SCREEN_WIDTH = screenSize.getWidth();
    public static final double SCREEN_HEIGHT = screenSize.getHeight();

    // Strings
    public static final String ARG_FILE = "-f";

    // Ints
    public static final int LINE_HEIGHT = 15;
    public static final int STR_OFFSET_WIDTH_CHAR = 3;
    public static final int STR_OFFSET_HEIGHT_CHAR = 5;

    // Floats
    public static final float TABLE_SIZE = 20;
}
