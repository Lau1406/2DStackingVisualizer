package nl.lkeijzer;

import nl.lkeijzer.Gui.MainFrame;
import nl.lkeijzer.Services.InputReader;

import java.io.File;

import static nl.lkeijzer.Constants.*;

/**
 * Created by Laurence Keijzer on 2017-05-04.
 */
public class Main {
    private MainFrame mMainFrame;
    private InputReader mInputReader;

    private static String mFileName;
    private File mFile;

    public Main() {
        if (mFileName != null) {
            mFile = new File(mFileName);
        }
        mInputReader = new InputReader();
        mMainFrame = new MainFrame("Visualizer", () -> mMainFrame.setRectangles(mInputReader.readInput(null)));
        mMainFrame.setRectangles(mInputReader.readInput(mFile));
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            // Check for input file
            if (args[i].equalsIgnoreCase(ARG_FILE)) {
                if (i + 1 < args.length) {
                    mFileName = args[i + 1];
                }
                continue;
            }
        }

        new Main();
    }
}
