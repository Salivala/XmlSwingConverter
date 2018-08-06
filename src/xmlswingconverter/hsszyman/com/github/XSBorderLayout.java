package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.Element;

import javax.swing.*;
import java.awt.*;
//import static xmlswingconverter.hsszyman.com.github.XmlSwingConverter.parseElementAsContainer;

public class XSBorderLayout implements ContainerProducer{
    @Override
    public JPanel parseElementAsContainer(Element currentParentElem, XmlSwingPage xsp) {
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new BorderLayout());
       /**
        ContainerProducer.invokeOnChildElements(currentParentElem, panel, (currElem, currContainer) -> {
            if (currElem.getTagName().endsWith("NORTH")) {
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.NORTH);
            }
            else if (currElem.getTagName().endsWith("SOUTH"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.SOUTH);
            else if (currElem.getTagName().endsWith("EAST"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.EAST);
            else if (currElem.getTagName().endsWith("WEST"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.WEST);
            else if (currElem.getTagName().endsWith("CENTER"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.CENTER);
        });
        return panel;
        **/
       return panel;
    }
}
