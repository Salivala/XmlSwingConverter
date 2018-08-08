package xmlswingpagefactory.interfaces;

import org.w3c.dom.Element;
import xmlswingpagefactory.XmlSwingPage;

import javax.swing.*;


public interface JListGenerator {
    JList<String> generateJList(Element elem, XmlSwingPage xsp);
}
