package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.Element;
import javax.swing.tree.DefaultMutableTreeNode;

@FunctionalInterface
public interface TreeNodeChildIterator {
    void elemTreeNodeChildIterator(Element elem, DefaultMutableTreeNode node);
}
