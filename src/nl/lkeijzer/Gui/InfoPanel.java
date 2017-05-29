package nl.lkeijzer.Gui;

import net.miginfocom.swing.MigLayout;
import nl.lkeijzer.Objects.Rectangle;
import nl.lkeijzer.Services.CustomMath;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Laurence on 2017-05-07.
 */
public class InfoPanel extends JPanel {

    private Rectangle[] mRectangles;
    private Dimension mContainer = new Dimension(0, 0);

    private JLabel mLabelBoxArea;
    private JLabel mLabelMinArea;
    private JLabel mLabelPercentageArea;
    private JLabel mLabelWidth;
    private JLabel mLabelHeight;
    private JLabel mLabelAmountRectangles;

    public InfoPanel(Rectangle[] rectangles) {
        mRectangles = rectangles;
        this.setLayout(new MigLayout());
        addComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
    }

    private void addComponents() {
        mLabelBoxArea = new JLabel();
        mLabelMinArea = new JLabel();
        mLabelPercentageArea = new JLabel();
        mLabelWidth = new JLabel();
        mLabelHeight = new JLabel();
        mLabelAmountRectangles = new JLabel();

        calcStuff();

        this.add(mLabelBoxArea, "wrap");
        this.add(mLabelMinArea, "wrap");
        this.add(mLabelPercentageArea, "wrap");
        this.add(mLabelWidth, "wrap");
        this.add(mLabelHeight, "wrap");
        this.add(mLabelAmountRectangles, "wrap");
    }

    private void calcStuff() {
        // Calculate everything
        CustomMath.calcContainer(mRectangles, mContainer);
        int minArea = 0;
        int maxHeight = 0;
        int maxWidth = 0;
        for (Rectangle rectangle : mRectangles) {
            minArea += rectangle.getHeight() * rectangle.getWidth();
            if (rectangle.getWidth() + rectangle.getBottomLeft().getX() > maxWidth) {
                maxWidth = (int) (rectangle.getWidth() + rectangle.getBottomLeft().getX());
            }
            if (rectangle.getHeight() + rectangle.getBottomLeft().getY() > maxHeight) {
                maxHeight= (int) (rectangle.getHeight() + rectangle.getBottomLeft().getY());
            }
        }

        // Set text in labels
        mLabelBoxArea.setText("Area Box: " + String.valueOf(maxHeight * maxWidth));
        mLabelMinArea.setText("Minimal Area: " + String.valueOf(minArea));
        mLabelPercentageArea.setText("Percentage Area: " + String.valueOf(Math.round((double) minArea / (double)
                (maxHeight * maxWidth) * 100)) + "%");
        mLabelWidth.setText("Width: " + String.valueOf((int) mContainer.getWidth()));
        mLabelHeight.setText("Height: " + String.valueOf((int) mContainer.getHeight()));
        mLabelAmountRectangles.setText("Amount of rectangles: " + String.valueOf(mRectangles.length));
    }

    public void setRectangles(Rectangle[] rectangles) {
        mRectangles = rectangles;
        calcStuff();
    }
}
