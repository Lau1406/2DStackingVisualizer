package nl.lkeijzer.Gui;

import javax.swing.*;
import java.awt.*;

import nl.lkeijzer.Objects.Rectangle;

import static nl.lkeijzer.Constants.*;

/**
 * Created by Laurence Keijzer on 2017-05-04.
 */
public class VisualizerPanel extends JPanel {

    private Dimension mContainer = new Dimension(0, 0);
    private Rectangle[] mRectangles = new Rectangle[0];


    public VisualizerPanel(LayoutManager lm) {
        super(lm);
    }

    public void setRectangles(Rectangle[] rectangles) {
        this.mRectangles = rectangles;
        calcContainer();
    }

    private void calcContainer() {
        int maxWidth = 0;
        int maxHeight = 0;
        for (Rectangle rectangle : mRectangles) {
            if (rectangle.getHeight() + rectangle.getBottomLeft().y > maxHeight) {
                maxHeight = rectangle.getHeight() + rectangle.getBottomLeft().y;
            }
            if (rectangle.getWidth() + rectangle.getBottomLeft().x > maxWidth) {
                maxWidth = rectangle.getWidth() + rectangle.getBottomLeft().x;
            }
        }
        mContainer.setSize(maxWidth, maxHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();

        g.drawRect(0, 0, width - 1, height - 1);

        Color oldColor = g.getColor();
        for (int i = 0; i < (int) TABLE_SIZE; i++) {
            g.setColor(Color.GRAY.brighter());
            // Axis
            int x = Math.round(width / TABLE_SIZE * i);
            g.drawLine(x, height, x, 0);

            int y = Math.round(height / TABLE_SIZE * i);
            g.drawLine(0, y, width, y);


            // Axis name
            g.setColor(Color.BLACK);

            // Don't print string if i == 0
            if (i == 0) {
                g.drawString("0", LINE_HEIGHT, height - LINE_HEIGHT);
                continue;
            }
            String str = String.valueOf(Math.round(mContainer.getWidth() / TABLE_SIZE * i));
            int offset = Math.round(width / TABLE_SIZE * i - (str.length() * STR_OFFSET_WIDTH_CHAR));
            g.drawString(str, offset, height - LINE_HEIGHT);

            str = String.valueOf(Math.round(mContainer.getHeight() - (mContainer.getHeight() / TABLE_SIZE * i)));
            offset = Math.round(height / TABLE_SIZE * i + STR_OFFSET_HEIGHT_CHAR);
            g.drawString(str, LINE_HEIGHT, offset);
        }
        g.setColor(oldColor);

        g.setColor(Color.BLACK);
        if (mRectangles == null) {
            return;
        }
        for (Rectangle r : mRectangles) {
            drawRect(r, g);
        }

    }

    @Override
    public void repaint() {
        super.repaint();
    }

    private void drawRect(Rectangle rect, Graphics g) {
        Color origColor = g.getColor();
        g.setColor(rect.getColor());
        int x, y, width, height;
        Point bottom = rect.getBottomLeft();
        Dimension dim = this.getSize();
        width = (int) rangeChange(mContainer.width, dim.width, rect.getWidth());
        height = (int) rangeChange(mContainer.height, dim.height, rect.getHeight());

        x = (int) rangeChange(mContainer.width, dim.width, bottom.x);
        y = this.getSize().height - height - (int) rangeChange(mContainer.height, dim.height, bottom.y);


        g.fillRect(x, y, width, height);
        g.setColor(origColor);
    }

    private double rangeChange(int start, int maxOld, int maxNew, double value) {
        int rangeOld = (maxOld - start);
        if (rangeOld == 0) {
            return start;
        } else {
            int rangeNew = (maxNew - start);
            return (((value - start) * rangeNew) / rangeOld) + start;
        }

    }

    // Assumes start at 0
    private double rangeChange(int maxOld, int maxNew, double value) {
        return rangeChange(0, maxOld, maxNew, value);
    }
}
