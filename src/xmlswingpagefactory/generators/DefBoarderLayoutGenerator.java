package xmlswingpagefactory.generators;

import org.w3c.dom.Element;
import xmlswingpagefactory.ConverterSuite;
import xmlswingpagefactory.XmlSwingPage;
import xmlswingpagefactory.interfaces.BorderLayoutGenerator;
import static xmlswingpagefactory.ConversionUtils.invokeOnChildElements;

import javax.swing.*;
import java.awt.*;

public class DefBoarderLayoutGenerator implements BorderLayoutGenerator {
    @Override
    public JPanel generateBorderLayoutPanel(Element elem, XmlSwingPage xsp, ConverterSuite delegates) {
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new BorderLayout());
        String name = elem.getAttribute("name");
        if (!name.equals("")) {
            xsp.setPanel(name, panel);
        }
        invokeOnChildElements(elem, panel, (currElem, currContainer) -> {
            if (currElem.getTagName().endsWith("NORTH")) {
                currContainer.add(delegates.router.routeToContainer(
                        ((Element) currElem.getChildNodes().item(1)),
                        xsp,
                        delegates
                ), BorderLayout.NORTH);
            }
            else if (currElem.getTagName().endsWith("SOUTH")) {
                currContainer.add(delegates.router.routeToContainer(
                        ((Element) currElem.getChildNodes().item(1)),
                        xsp,
                        delegates
                ), BorderLayout.SOUTH);
            }
            else if (currElem.getTagName().endsWith("WEST")) {
                currContainer.add(delegates.router.routeToContainer(
                        ((Element) currElem.getChildNodes().item(1)),
                        xsp,
                        delegates
                ), BorderLayout.WEST);
            }
            else if (currElem.getTagName().endsWith("EAST")) {
                currContainer.add(delegates.router.routeToContainer(
                        ((Element) currElem.getChildNodes().item(1)),
                        xsp,
                        delegates
                ), BorderLayout.EAST);
            }
            else if (currElem.getTagName().endsWith("CENTER")) {
                currContainer.add(delegates.router.routeToContainer(
                        ((Element) currElem.getChildNodes().item(1)),
                        xsp,
                        delegates
                ), BorderLayout.CENTER);
            }
        });
        return panel;
    }

    void addToBorderLayout (String direction, JPanel panel, ConverterSuite delegates) {
    }
}
