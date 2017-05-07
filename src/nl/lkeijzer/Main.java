package nl.lkeijzer;

import nl.lkeijzer.Gui.MainFrame;
import nl.lkeijzer.Services.InputReader;

import java.io.File;

/**
 * Created by Laurence Keijzer on 2017-05-04.
 */
public class Main {
    private MainFrame mMainFrame;
    private InputReader mInputReader;

    public Main() {
        File file = new File("testInputFile.txt");
//        file = null;
        mInputReader = new InputReader();
        mMainFrame = new MainFrame("Visualizer", () -> mMainFrame.setRectangles(mInputReader.readInput(null)));
        mMainFrame.setRectangles(mInputReader.readInput(file));
    }

    public static void main(String[] args) {
        new Main();
    }
}
