package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;


public class XmlSwingConverter {
    private DocumentBuilderFactory builderFactory;
    private DocumentBuilder builder;
    private Document xmlDoc;
    private Map<String, ActionListener> actions;
    private XmlSwingPage page;
    public JFrame frame;
    private Path path;

    public static void main(String[] args) {
        HashMap<String, ActionListener> actions = new HashMap<>();
        XmlSwingConverter converter = new XmlSwingConverter(Paths.get("./src", "example.xml"), actions);
    }


    /**
     * @param xmlPath : path of the xml file that is formatted as an XMLSwingPage
     * @param actions : hashmap of actionListeners
     */
    public XmlSwingConverter(Path xmlPath, Map<String, ActionListener> actions) {
        this.path = xmlPath;
        this.actions = actions;
        File xmlFile = xmlPath.toFile();
        initXmlHandlingFields(xmlFile);
        frame = new JFrame();
        frame.setContentPane(parseElementAsContainer((Element) xmlDoc.getDocumentElement().getChildNodes().item(1)));
        System.out.println(frame.getContentPane().getLayout().toString());
        frame.setSize(399,399);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * @param currentParentElem
     * @return
     */
    private Container parseElementAsContainer(Element currentParentElem) {
        String s = currentParentElem.getTagName();
        switch (s) {
            case "BorderLayout":
                return getBorderLayoutPanel(currentParentElem);
            case "FlowLayout":
                return getFlowLayoutPanel(currentParentElem);
            case "JButton":
                return getJButton(currentParentElem);
            case "JLabel":
                return getJLabel(currentParentElem);
            case "JTextField":
                return getJTextField(currentParentElem);
        }
        return null;
    }

    /**
     *
     * @param elem
     * @return
     */
    private JTextField getJTextField(Element elem) {
        Text textNode = (Text) elem.getFirstChild();
        JTextField field = new JTextField();
        field.setText(textNode.getData().trim());
        return field;
    }

    /**
     *
     * @param elem
     * @return
     */
    private JLabel getJLabel(Element elem) {
        Text textNode = (Text) elem.getFirstChild();
        JLabel label = new JLabel(textNode.getData().trim());
        return label;
    }

    /**
     * @param elem : current element
     * @return button that was produced
     */
    private JButton getJButton(Element elem) {
        JButton button = new JButton();
        String methodName = elem.getAttribute("action");
        button.addActionListener(actions.get(methodName));
        button.setVisible(true);
        return button;
    }

    private JPanel getBorderLayoutPanel(Element currentParentElem) {
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new BorderLayout());
        invokeOnChildElements(currentParentElem, panel, (currElem, currContainer) -> {
            if (currElem.getTagName().endsWith("NORTH"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1)), BorderLayout.NORTH);
            else if (currElem.getTagName().endsWith("SOUTH"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1)), BorderLayout.SOUTH);
            else if (currElem.getTagName().endsWith("EAST"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1)), BorderLayout.EAST);
            else if (currElem.getTagName().endsWith("WEST"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1)), BorderLayout.WEST);
        });
        return panel;
    }

    private JPanel getFlowLayoutPanel(Element currentParentElem) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        invokeOnChildElements(currentParentElem, panel, (elem, currContainer) -> {
            currContainer.add(parseElementAsContainer(elem));
        });
        return panel;
    }

    private void invokeOnChildElements(Element elem, Container container, ChildElemIterator func) {
        NodeList nodes = elem.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                Element subjectElem = ((Element) nodes.item(i));
                func.elemIteratorMethod(subjectElem, container);
            }
        }
    }

    public void addAction(String name, ActionListener action) {
        actions.put(name, action);
        frame.setContentPane(parseElementAsContainer((Element) xmlDoc.getDocumentElement().getChildNodes().item(1)));
    }


    /**
     * @param xmlFile : file to generate interface from
     */
    private void initXmlHandlingFields(File xmlFile) {
        builderFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = builderFactory.newDocumentBuilder();
            xmlDoc = builder.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
