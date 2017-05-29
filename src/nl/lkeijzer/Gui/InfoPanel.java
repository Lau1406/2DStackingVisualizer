package nl.lkeijzer.Gui;

import net.miginfocom.swing.MigLayout;
import nl.lkeijzer.Objects.Rectangle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Laurence on 2017-05-07.
 */
public class InfoPanel extends JPanel {

    private Rectangle[] mRectangles;

    private JLabel mLabelBox;
    private JLabel mLabelMin;
    private JLabel mLabelPercentage;

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
        mLabelBox = new JLabel();
        mLabelMin = new JLabel();
        mLabelPercentage = new JLabel();

        calcStuff();

        this.add(mLabelBox, "wrap");
        this.add(mLabelMin, "wrap");
        this.add(mLabelPercentage, "wrap");
    }

    private void calcStuff() {
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
        mLabelBox.setText("Area Box: " + String.valueOf(maxHeight * maxWidth));
        mLabelMin.setText("Minimal Area: " + String.valueOf(minArea));
        mLabelPercentage.setText("Percentage Area: " + String.valueOf(Math.round((double) minArea / (double)
                (maxHeight * maxWidth) * 100)) + "%");
    }

    public void setRectangles(Rectangle[] rectangles) {
        mRectangles = rectangles;
        calcStuff();
    }
}
