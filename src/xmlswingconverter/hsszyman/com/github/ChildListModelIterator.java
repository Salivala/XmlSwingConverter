package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.Element;

import javax.swing.*;

@FunctionalInterface
public interface ChildListModelIterator {
    void elemChildIteratorMethod(Element current, DefaultListModel<String> listModel);
}
