package nl.lkeijzer;

import nl.lkeijzer.Gui.MainFrame;
import nl.lkeijzer.Objects.Rectangle;
import nl.lkeijzer.Services.InputReader;

import javax.swing.*;
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
        Rectangle[] rectangles = mInputReader.readInput(mFile);
        mMainFrame = new MainFrame("Visualizer", rectangles, () -> mMainFrame.setRectangles(mInputReader.readInput(null)));
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
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        new Main();
    }
}
