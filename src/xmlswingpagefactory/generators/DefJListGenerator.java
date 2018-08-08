package xmlswingpagefactory.generators;

import org.w3c.dom.Element;
import xmlswingpagefactory.XmlSwingPage;
import xmlswingpagefactory.interfaces.JListGenerator;

import javax.swing.*;

public class DefJListGenerator implements JListGenerator {
    @Override
    public JList<String> generateJList(Element elem, XmlSwingPage xsp) {
        String name = elem.getAttribute("name");
        JList<String> list = new JList<>();
        if (!name.equals("")) {
            xsp.setList(name, list);
        }
        return list;
    }
}
