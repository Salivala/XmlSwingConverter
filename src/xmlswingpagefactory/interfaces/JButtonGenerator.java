package xmlswingpagefactory.interfaces;

import org.w3c.dom.Element;
import xmlswingpagefactory.XmlSwingPage;

import javax.swing.*;

public interface JButtonGenerator {
    JButton generateJButton(Element elem, XmlSwingPage xsp);
}
