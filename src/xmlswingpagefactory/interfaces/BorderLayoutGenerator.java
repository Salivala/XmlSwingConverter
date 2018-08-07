package xmlswingpagefactory.interfaces;

import org.w3c.dom.Element;
import xmlswingpagefactory.ConverterSuite;
import xmlswingpagefactory.XmlSwingPage;

import javax.swing.*;

public interface BorderLayoutGenerator {
    JPanel generateBorderLayoutPanel(Element elem, XmlSwingPage xsp, ConverterSuite delegates);
}
