package xmlswingpagefactory;

import org.w3c.dom.Element;
import xmlswingpagefactory.interfaces.TagRouter;

import javax.management.modelmbean.XMLParseException;
import javax.swing.*;
import java.awt.*;

public class DefaultTagRouter implements TagRouter {
    @Override
    public Container routeToContainer(Element currentParentElem, XmlSwingPage xsp, ConverterSuite delegates) {
        String s = currentParentElem.getTagName();
        switch (s) {
            case "FlowLayout":
                return delegates.flowLayoutPanelGen.generateFlowLayoutPanel(currentParentElem, xsp, delegates);
            case "BorderLayout":
                return delegates.borderLayoutGenerator.generateBorderLayoutPanel(currentParentElem, xsp, delegates);
            case "BoxLayout":
                return delegates.boxLayoutGenerator.generateBoxLayoutPanel(currentParentElem, xsp, delegates);
            case "JButton":
                return delegates.jButtonGenerator.generateJButton(currentParentElem,xsp);
            case "JLabel":
                return delegates.jLabelGenerator.generateJLabel(currentParentElem, xsp);
            case "JComboBox":
                return delegates.jComboBoxGenerator.generateJComboBox(currentParentElem, xsp);
            case "JList":
                return delegates.jListGenerator.generateJList(currentParentElem, xsp);
            case "JScrollPane":
            default:
                xsp.getFrame().setVisible(false);
                xsp.getFrame().setContentPane(new JPanel());
                throw new Error("Element " + s + " does not exist");
        }
    }
}
