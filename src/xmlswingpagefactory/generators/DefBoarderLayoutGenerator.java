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
    public Container generateBorderLayoutPanel(Element elem, XmlSwingPage xsp, ConverterSuite delegates) {
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new BorderLayout());
        String name = elem.getAttribute("name");
        if (!name.equals("")) {
            xsp.setPanel(name, panel);
        }
        invokeOnChildElements(elem, panel, (currElem, currContainer) -> {
            if (currElem.getTagName().endsWith("NORTH"))
                addToLayout(currContainer, currElem, xsp, delegates, BorderLayout.NORTH);
            else if (currElem.getTagName().endsWith("SOUTH"))
                addToLayout(currContainer, currElem, xsp, delegates, BorderLayout.SOUTH);
            else if (currElem.getTagName().endsWith("WEST"))
                addToLayout(currContainer, currElem, xsp, delegates, BorderLayout.WEST);
            else if (currElem.getTagName().endsWith("EAST"))
                addToLayout(currContainer, currElem, xsp, delegates, BorderLayout.EAST);
            else if (currElem.getTagName().endsWith("CENTER"))
                addToLayout(currContainer, currElem, xsp, delegates, BorderLayout.CENTER);
        });
        return elem.getAttribute("scroll").equals("true") ? new JScrollPane(panel) : panel;
    }

    private void addToLayout(Container container, Element elem, XmlSwingPage xsp, ConverterSuite delegates, String direction) {
        container.add(delegates.router.routeToContainer(
                ((Element) elem.getChildNodes().item(1)),
                xsp,
                delegates
        ), direction);
    }
}
