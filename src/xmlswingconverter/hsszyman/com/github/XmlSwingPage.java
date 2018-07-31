package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.*;

public class XmlSwingPage {
    public JPanel jpanel = new JPanel();
    public JFrame frame;
    XmlSwingPage() {
        frame = new JFrame();
    }

    void setLayout(LayoutManager layout) {
        jpanel.setLayout(layout);
    }

    public boolean setVisible() {
        if (frame != null) {
            frame.setVisible(true);
            return true;
        }
        return false;
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }
}
