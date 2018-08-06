package xmlswingpagefactory.interfaces;

import org.w3c.dom.Element;
import xmlswingpagefactory.XmlSwingPage;

import javax.swing.*;

public interface JLabelGenerator {
    public JLabel generateJLabel(Element elem, XmlSwingPage xsp);
}
