package nl.lkeijzer.Gui;

import net.miginfocom.swing.MigLayout;
import nl.lkeijzer.Constants;
import nl.lkeijzer.Objects.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static nl.lkeijzer.Constants.*;

/**
 * Created by Laurence on 2017-05-05.
 */
public class MainFrame extends JFrame implements WindowListener, ActionListener, ComponentListener {

    private boolean mFixedHeight;
    private boolean mRotationsAllowed;
    private int mMaxHeight;

    private Rectangle[] mRectangles;

    private VisualizerPanel mVisualizerPanel;
    private InfoPanel mInfoPanel;
    private JPanel mWrapper;

    private JButton mButtonReadData;
    private JButton mButtonExit;

    private Callback mCallback;



    public MainFrame(String title, Rectangle[] rectangles, boolean fixedHeight, boolean rotationsAllowed,
                     int maxHeight, Callback callback) {
        super(title);
        mFixedHeight = fixedHeight;
        mRotationsAllowed = rotationsAllowed;
        mMaxHeight = maxHeight;
        mCallback = callback;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(this);
        this.addComponentListener(this);
        int min = (int) Math.min(SCREEN_WIDTH, SCREEN_HEIGHT - TASK_BAR_OFFSET); // Tiny offset for the task-bar
        this.setSize(min, min);

        mWrapper = new JPanel(new MigLayout());

        mVisualizerPanel = new VisualizerPanel(rectangles, mFixedHeight, mRotationsAllowed, mMaxHeight);
        mInfoPanel = new InfoPanel(rectangles);

        mButtonReadData = new JButton("Get Data");
        mButtonExit = new JButton("Exit");
        mButtonReadData.addActionListener(this);
        mButtonExit.addActionListener(this);

        mWrapper.add(mVisualizerPanel, "cell 0 0");
        mWrapper.add(mInfoPanel, "cell 1 0");
        mWrapper.add(mButtonReadData, "cell 0 1, span, split 2, center");
        mWrapper.add(mButtonExit, "cell 1 1");

        this.add(mWrapper);
        this.repaint();

        this.setMinimumSize(new Dimension(MIN_SCREEN_SIZE, MIN_SCREEN_SIZE));
        this.setLocation(
                (int) (SCREEN_WIDTH / 2 - this.getWidth() / 2),
                (int) (Constants.SCREEN_HEIGHT / 2 - this.getHeight() / 2 - TASK_BAR_OFFSET / 2));
        Dimension dim = this.getSize();
        mVisualizerPanel.setPreferredSize(new Dimension((int) (dim.getWidth() * 0.75), (int) SCREEN_HEIGHT));
        mInfoPanel.setPreferredSize(new Dimension((int) (dim.getWidth() * 0.25), (int) SCREEN_HEIGHT));
        this.setVisible(true);
    }

    public void setRectangles(Rectangle[] rectangles) {
        this.mRectangles = rectangles;
        mVisualizerPanel.setRectangles(rectangles);
        mInfoPanel.setRectangles(rectangles);
        draw();
    }

    private void draw() {
        mVisualizerPanel.repaint();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == mButtonExit) {
            this.dispose();
        } else if (src == mButtonReadData) {
            new Thread(() -> mCallback.readData()).start();
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Dimension dim = this.getSize();
        if (mVisualizerPanel != null) {
            mVisualizerPanel.setPreferredSize(new Dimension((int) (dim.getWidth() * 0.75), (int) SCREEN_HEIGHT));
        }
        if (mInfoPanel != null) {
            mInfoPanel.setPreferredSize(new Dimension((int) (dim.getWidth() * 0.25), (int) SCREEN_HEIGHT));
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    public interface Callback {
        void readData();
    }
}

