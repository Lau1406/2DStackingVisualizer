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
    public static final String ARG_STRING_INPUT = "-i";

    // Ints
    public static final int LINE_HEIGHT = 5;
    public static final int STR_OFFSET_WIDTH_CHAR = 3;
    public static final int STR_OFFSET_HEIGHT_CHAR = 5;
    public static final int TASK_BAR_OFFSET = 50;
    public static final int MIN_SCREEN_SIZE = 600;

    // Floats
    public static final float TABLE_SIZE = 20;
}
