package xmlswingconverter.hsszyman.com.github;


import org.w3c.dom.Element;

import java.awt.*;

@FunctionalInterface
public interface ChildElemIterator<T> {
    void elemIteratorMethod(Element elem, T gen);
}
