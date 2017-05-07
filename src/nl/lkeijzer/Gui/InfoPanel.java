package nl.lkeijzer.Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Laurence on 2017-05-07.
 */
public class InfoPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());

        g.drawString("just testing", 0, 0);
    }
}
