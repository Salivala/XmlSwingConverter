package xmlswingpagefactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.function.BiConsumer;

public class ConversionUtils {
    /**
     * @param elem
     * @param subject : subject to be operated on WARNING : CHANGES THIS VALUE
     * @param consumer
     * @param <T>
     */
    public static <T> void invokeOnChildElements(Element elem, T subject, BiConsumer<Element, T> consumer) {
        NodeList nodes = elem.getChildNodes();
        for ( int i = 0; i < nodes.getLength(); i++) {
            if  (nodes.item(i) instanceof Element) {
                Element subjectElem = ((Element) nodes.item(i));
                consumer.accept(subjectElem, subject);
            }
        }
    }
}
