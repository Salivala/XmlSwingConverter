package xmlswingpagefactory.generators;

import org.w3c.dom.Element;
import org.w3c.dom.Text;
import xmlswingpagefactory.XmlSwingPage;
import xmlswingpagefactory.interfaces.JLabelGenerator;

import javax.swing.*;

public class DefaultJLabelGenerator implements JLabelGenerator {
    @Override
    public JLabel generateJLabel(Element elem, XmlSwingPage xsp) {
        Text textNode = (Text) elem.getFirstChild();
        JLabel label = new JLabel(textNode.getData().trim());
        xsp.setLabel(elem.getAttribute("name"), label);
        return label;
    }
}
