package xmlswingpagefactory.generators;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import xmlswingpagefactory.XmlSwingPage;
import xmlswingpagefactory.interfaces.JComboBoxGenerator;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DefJComboBoxGenerator implements JComboBoxGenerator {
    @Override
    public JComboBox<String> generateJComboBox(Element currElem, XmlSwingPage xsp) {
        JComboBox<String> comboBox = new JComboBox<>();
        String name = currElem.getAttribute("name");
        if (!name.equals("")) { xsp.setComboBox(name, comboBox); }
        if (currElem.hasChildNodes()) {
            DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
            String text = ((Text) currElem.getFirstChild()).getData().trim();
            Stream.of(text.split(" +")).forEach(box::addElement);
            comboBox.setModel(box);
        }
        return comboBox;
    }
}
