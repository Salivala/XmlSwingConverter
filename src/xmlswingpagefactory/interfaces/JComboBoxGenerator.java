package xmlswingpagefactory.interfaces;

import org.w3c.dom.Element;
import xmlswingpagefactory.XmlSwingPage;

import javax.swing.*;

public interface JComboBoxGenerator {
    JComboBox<String> generateJComboBox(Element currElem, XmlSwingPage xsp);
}
