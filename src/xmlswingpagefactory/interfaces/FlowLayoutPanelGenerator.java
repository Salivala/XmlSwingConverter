package xmlswingpagefactory.interfaces;

import org.w3c.dom.Element;
import xmlswingpagefactory.ConverterSuite;
import xmlswingpagefactory.XmlSwingPage;

import javax.swing.*;

public interface FlowLayoutPanelGenerator {
    JPanel generateFlowLayoutPanel(Element currentParentElem, XmlSwingPage xsp, ConverterSuite delegates );
}
