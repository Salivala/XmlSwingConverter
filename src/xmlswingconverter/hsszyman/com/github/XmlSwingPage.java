package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class XmlSwingPage {
    private JPanel jpanel = new JPanel();
    private JFrame frame;
    XmlSwingPage(String name) {
        frame = new JFrame(name);
    }

    void setLayout(BorderLayout layout) {
        jpanel.setLayout(layout);
    }

    void setLayout(FlowLayout layout) {
        jpanel.setLayout(layout);
    }
    public boolean setVisible() {
        if (frame != null) {
            frame.setVisible(true);
            return true;
        }
        return false;
    }
}
