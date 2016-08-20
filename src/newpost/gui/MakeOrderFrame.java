package newpost.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sasha on 18.08.2016.
 */
public class MakeOrderFrame extends JFrame {

    public MakeOrderFrame() {
        setTitle("make order");
        setSize(600, 600);
        //CENTER
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize(), fSize = getSize();

        if (fSize.height > sSize.height) {fSize.height = sSize.height;}
        if (fSize.width  > sSize.width)  {fSize.width = sSize.width;}
        setLocation ((sSize.width - fSize.width)/2,
                (sSize.height - fSize.height)/2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        MakeOrderFrame mof = new MakeOrderFrame();
    }
}
