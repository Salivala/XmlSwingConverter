package xmlswingpagefactory.interfaces;

import org.w3c.dom.Element;
import xmlswingpagefactory.ConverterSuite;
import xmlswingpagefactory.XmlSwingPage;

import java.awt.*;

/**
 * Interface defining behavior for a class that implements TagRouter.
 * Depending on the currentParentElem tag name, route to a Container generating delegate that eventually will produce
 * a swing container
 */
public interface TagRouter {
    Container routeToContainer(Element currentParentElem, XmlSwingPage xsp, ConverterSuite delegates);
}
