package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.util.function.BiConsumer;

public abstract class XSContainer {
    public abstract Container evalElemToContainer(Element elem);

    @Override public abstract String toString();

    protected static <T> void invokeOnChildElements(Element elem, T subject, BiConsumer<Element, T> func) {
        NodeList nodes = elem.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                Element subjectElem = ((Element) nodes.item(i));
                func.accept(subjectElem, subject);
            }
        }
    }
}
