package xmlswingpagefactory.generators;

import org.w3c.dom.Element;
import org.w3c.dom.Text;
import xmlswingpagefactory.XmlSwingPage;
import xmlswingpagefactory.interfaces.JButtonGenerator;

import javax.swing.*;

/**
 * Default JButton generator responsible for a standard button
 */
public class DefaultJButtonGenerator implements JButtonGenerator {
    @Override
    public JButton generateJButton(Element elem, XmlSwingPage xsp) {
        JButton button = new JButton();
        Text textNode = (Text) elem.getFirstChild();
        button.setText(textNode.getData().trim());
        button.setVisible(true);
        return button;
    }
}
