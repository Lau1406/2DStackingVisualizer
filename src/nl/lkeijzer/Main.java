package nl.lkeijzer;

import nl.lkeijzer.Gui.MainFrame;
import nl.lkeijzer.Objects.Rectangle;
import nl.lkeijzer.Services.InputReader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static nl.lkeijzer.Constants.ARG_FILE;
import static nl.lkeijzer.Constants.ARG_STRING_INPUT;

/**
 * Created by Laurence Keijzer on 2017-05-04.
 */
public class Main {
    private MainFrame mMainFrame;
    private InputReader mInputReader;
    private boolean mIsReadingData = false;

    private static String mFileName;
    private File mFile;

    public Main() {
        if (mFileName != null) {
            mFile = new File(mFileName);
        }
        mInputReader = new InputReader();
        Rectangle[] rectangles = mInputReader.readInput(mFile);
        mMainFrame = new MainFrame("Visualizer", rectangles, mInputReader.isFixedHeight(),
                mInputReader.isRotationsAllowed(), mInputReader.getHeight(), new MainFrame.Callback() {
            @Override
            public void readData() {
                if (mIsReadingData) {
                    return;
                }
                mIsReadingData = true;
                mInputReader.readInput(null);
                mMainFrame.setRectangles(mInputReader.getRectangles(), mInputReader.isFixedHeight(), mInputReader
                        .isRotationsAllowed(), mInputReader.getHeight());
                mIsReadingData = false;
            }
        });
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
            // Check for input file
            if (args[i].equalsIgnoreCase(ARG_STRING_INPUT)) {
                if (i + 1 < args.length) {
                    mFileName = "tempFile.txt";
                    try{
                        PrintWriter writer = new PrintWriter(mFileName, "UTF-8");
                        writer.write(args[i + 1]);
                        writer.close();
                    } catch (IOException e) {
                        // do something
                    }
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
