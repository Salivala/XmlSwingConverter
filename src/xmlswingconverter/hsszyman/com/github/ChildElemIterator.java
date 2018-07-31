package xmlswingconverter.hsszyman.com.github;


import org.w3c.dom.Element;

import java.awt.*;

@FunctionalInterface
public interface ChildElemIterator {
    void elemIteratorMethod(Element elem, Container container);
}
