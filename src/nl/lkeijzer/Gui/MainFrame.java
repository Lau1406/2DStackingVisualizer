package nl.lkeijzer.Gui;

import nl.lkeijzer.Constants;
import nl.lkeijzer.Objects.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Laurence on 2017-05-05.
 */
public class MainFrame extends JFrame implements WindowListener, ActionListener {
//    private boolean mFixedHeight = false;
//    private boolean mRotationsAllowed = false;
//    private int mMaxHeight = 0;

    private Rectangle[] mRectangles;
    private GridBagConstraints c;

    private VisualizerPanel mVisualizerPanel;
    private InfoPanel mInfoPanel;
    private JPanel mJPanelButtons;

    private JButton mButtonReadData;
    private JButton mButtonExit;

    private Callback mCallback;



    public MainFrame(String title, Callback callback) {
        super(title);
        mCallback = callback;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(this);
        this.setSize(1600, 900);
        this.setLayout(new BorderLayout());

        c = new GridBagConstraints();

        mVisualizerPanel = new VisualizerPanel(new GridBagLayout());
        mInfoPanel = new InfoPanel();
        mJPanelButtons = new JPanel(new GridBagLayout());

        mButtonReadData = new JButton("Get Data");
        mButtonExit = new JButton("Exit");
        mButtonReadData.addActionListener(this);
        mButtonExit.addActionListener(this);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        mJPanelButtons.add(mButtonReadData, c);
        c.gridx = 1;
        mJPanelButtons.add(mButtonExit, c);

        this.add(mJPanelButtons, BorderLayout.SOUTH);
//        this.add(mInfoPanel, BorderLayout.WEST);
        this.add(mVisualizerPanel);
        this.repaint();


        // TODO: set min size dynamically
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocation(
                (int) (Constants.SCREEN_WIDTH / 2 - this.getWidth() / 2),
                (int) (Constants.SCREEN_HEIGHT / 2 - this.getHeight() / 2));
        this.setVisible(true);
    }

    private void resetGridBagConstraints(GridBagConstraints gb) {
        gb.gridx = 0;
        gb.gridy = 0;
        gb.gridheight = 1;
        gb.gridwidth = 1;
    }

    public void setRectangles(Rectangle[] rectangles) {
        this.mRectangles = rectangles;
        mVisualizerPanel.setRectangles(rectangles);
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
            // TODO: make call async
            mCallback.readData();
        }
    }
    public interface Callback {
        void readData();
    }
}

