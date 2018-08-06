package xmlswingpagefactory.generators;

import org.w3c.dom.Element;
import org.w3c.dom.Text;
import xmlswingpagefactory.ConverterSuite;
import xmlswingpagefactory.XmlSwingPage;
import xmlswingpagefactory.interfaces.FlowLayoutPanelGenerator;

import javax.swing.*;
import java.awt.*;
import static xmlswingpagefactory.ConversionUtils.invokeOnChildElements;

public class DefaultFlowLayoutPanelGenerator implements FlowLayoutPanelGenerator {
    @Override
    public JPanel generateFlowLayoutPanel(Element currentParentElem, XmlSwingPage xsp, ConverterSuite delegates) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        invokeOnChildElements(currentParentElem, panel, (elem, currContainer) -> {
            Text textNode = (Text) elem.getFirstChild();
            System.out.println(textNode.getData().trim());
            currContainer.add(delegates.router.routeToContainer(elem, xsp, delegates));
        });
        return panel;
    }
}
