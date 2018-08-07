package xmlswingpagefactory.generators;

import org.w3c.dom.Element;
import xmlswingpagefactory.ConverterSuite;
import xmlswingpagefactory.XmlSwingPage;
import xmlswingpagefactory.interfaces.BoxLayoutGenerator;
import static xmlswingpagefactory.ConversionUtils.invokeOnChildElements;

import javax.swing.*;

public class DefBoxLayoutGenerator implements BoxLayoutGenerator {
    @Override
    public JPanel generateBoxLayoutPanel(Element elem, XmlSwingPage xsp, ConverterSuite delegates) {
        JPanel panel = new JPanel();
        String layoutOrientation = elem.getAttribute("orientation");
        BoxLayout layout;
        if (!layoutOrientation.equals("")) {
            if (layoutOrientation.equals("vertical")) {
                layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            }
            else
                layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        }
        else {
            layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        }
        panel.setLayout(layout);
        invokeOnChildElements(elem, panel, (currElem, currPanel) -> {
            currPanel.add(delegates.router.routeToContainer(currElem, xsp, delegates));
        });
        return panel;
    }
}
