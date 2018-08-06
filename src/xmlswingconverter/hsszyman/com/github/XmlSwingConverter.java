package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.*;

public class XmlSwingConverter {
    public static XmlSwingPage getNewXmlSwingPage(Path xmlPath, Map<String, ActionListener> actions) {
        File xmlFile = xmlPath.toFile();
        Document xmlDoc = getXmlDocument(xmlFile);
        XmlSwingPage page = new XmlSwingPage();
        page.setActions(actions);
        Thread generateXmlSwingComponents = new Thread(() -> {
            Element rootElem = ((Element) xmlDoc.getDocumentElement().getChildNodes().item(1));
            page.getFrame().setContentPane(parseElementAsContainer(rootElem, page));
        });
        generateXmlSwingComponents.start();
        try {
            generateXmlSwingComponents.join();
            page.getFrame().setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        configureFrame(page, xmlDoc);
        return page;
    }

    private static Container parseElementAsContainer(Element currentParentElem, XmlSwingPage xsp) {
        String s = currentParentElem.getTagName();
        switch (s) {
            case "BorderLayout":
                return getBorderLayoutPanel(currentParentElem, xsp);
            case "FlowLayout":
                return getFlowLayoutPanel(currentParentElem, xsp);
            case "BoxLayout":
                return getBoxLayoutPanel(currentParentElem, xsp);
            case "JButton":
                return getJButton(currentParentElem, xsp);
            case "JLabel":
                return getJLabel(currentParentElem, xsp);
            case "JTextField":
                return getJTextField(currentParentElem, xsp);
            case "JList":
                return getJList(currentParentElem, xsp);
            case "JTree":
                return getJTree(currentParentElem, xsp);
            default:
                return new JLabel("oh no");
        }
    }


    /**
     * Configure JFrame using common defaults and XML Attributes from parent node
     */
    private static void configureFrame(XmlSwingPage page, Document xmlDoc) {
        String frameSize = xmlDoc.getDocumentElement().getAttribute("size");

        if (!frameSize.equals("")) {
            int x, y;
            x = Integer.parseInt(frameSize.substring(0, frameSize.indexOf("x")));
            y = Integer.parseInt(frameSize.substring(frameSize.indexOf("x") + 1), frameSize.length());
            page.getFrame().setSize(x,y);
        }
        page.getFrame().setTitle(xmlDoc.getDocumentElement().getAttribute("title"));
        page.getFrame().setLocationRelativeTo(null);
        page.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * @param elem
     * @return
     */
    private static JScrollPane getJTree(Element elem, XmlSwingPage xsp) {
        JTree tree = new JTree(getTreeNode(elem, new DefaultMutableTreeNode(elem.getAttribute("title"))));
        xsp.setTree(elem.getAttribute("name"), tree);
        return new JScrollPane(tree); // package it inside of a scrollpane
    }

    private static DefaultMutableTreeNode getTreeNode(Element elem, DefaultMutableTreeNode node) {
        invokeOnChildElements(elem, node, (currElem, currNode) -> {
            if (currElem.getTagName().equalsIgnoreCase("node")) {
                DefaultMutableTreeNode newNode = getTreeNode(currElem, new DefaultMutableTreeNode(currElem.getAttribute("title")));
                currNode.add(newNode);
            }
            else if (currElem.getTagName().equalsIgnoreCase("content")) {
                String text = (currElem.getFirstChild() instanceof Text) ? ((Text) currElem.getFirstChild()).getData().trim() : "";
                if (!text.equals("")) {
                    currNode.add(new DefaultMutableTreeNode(text));
                }
            }
        });
        return node;
    }
    /**
     *
     * @param elem
     * @return
     */
    private static JTextField getJTextField(Element elem, XmlSwingPage xsp) {
        Text textNode = (Text) elem.getFirstChild();
        JTextField field = new JTextField();
        field.setText(textNode.getData().trim());
        String textFieldColumns = elem.getAttribute("columns");
        if (!textFieldColumns.equals("")) {
            field.setColumns(Integer.valueOf(textFieldColumns));
        }

        xsp.setTextFields(elem.getAttribute("name"), field);
        return field;
    }


    /**
     * @param elem : Current element in the DOM
     * @return a new JList
     */
    private static JList getJList(Element elem, XmlSwingPage xsp) {
        JList<String> list = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        list.setModel(listModel);
        xsp.setList(elem.getAttribute("name"), list);
        invokeOnChildElements(elem, listModel, (currElem, currModel) -> {
            Text textNode = (Text) currElem.getFirstChild();
            String listStr = textNode.getData().trim();
            currModel.addElement(listStr);
        });
        list.setVisible(true);
        return list;
    }

    /**
     *
     * @param elem
     * @return
     */
    private static JLabel getJLabel(Element elem, XmlSwingPage xsp) {
        Text textNode = (Text) elem.getFirstChild();
        JLabel label = new JLabel(textNode.getData().trim());
        xsp.setLabel(elem.getAttribute("name"), label);
        return label;
    }

    /**
     * @param elem : current element
     * @return button that was produced
     */
    private static JButton getJButton(Element elem, XmlSwingPage xsp) {
        JButton button = new JButton();
        String methodName = elem.getAttribute("action");
        Text textNode = (Text) elem.getFirstChild();
        button.setText(textNode.getData().trim());
        button.addActionListener(xsp.getAction(methodName));
        button.setVisible(true);
        xsp.setButton(elem.getAttribute("name"), button);
        return button;
    }

    /**
     *
     * @param currentParentElem : current Element in the dom
     * @return new Jpanel container a border layout
     */
    private static JPanel getBorderLayoutPanel(Element currentParentElem, XmlSwingPage xsp) {
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new BorderLayout());
        invokeOnChildElements(currentParentElem, panel, (currElem, currContainer) -> {
            if (currElem.getTagName().endsWith("NORTH"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.NORTH);
            else if (currElem.getTagName().endsWith("SOUTH"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.SOUTH);
            else if (currElem.getTagName().endsWith("EAST"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.EAST);
            else if (currElem.getTagName().endsWith("WEST"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.WEST);
            else if (currElem.getTagName().endsWith("CENTER"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1), xsp), BorderLayout.CENTER);
        });
        return panel;
    }

    private static JPanel getFlowLayoutPanel(Element currentParentElem, XmlSwingPage xsp) {
        JPanel panel = new JPanel();
        FlowLayout layout = new FlowLayout();
        panel.setLayout(new FlowLayout());
        invokeOnChildElements(currentParentElem, panel, (elem, currContainer) -> {
            currContainer.add(parseElementAsContainer(elem, xsp));
        });

        return panel;
    }

    private static JPanel getBoxLayoutPanel(Element currentParentElem, XmlSwingPage xsp) {
        JPanel panel = new JPanel();
        String layoutOrientation = currentParentElem.getAttribute("orientation");
        BoxLayout layout;
        if (!layoutOrientation.equals("")) {
            if (layoutOrientation.equals("vertical"))
                layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            else
                layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        }
        else {
            layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        }
        panel.setLayout(layout);
        invokeOnChildElements(currentParentElem, panel, (currElem, currPanel) -> {
            currPanel.add(parseElementAsContainer(currElem, xsp));
        });

        return panel;
    }

    /**
     *
     * @param elem element to get the child elements of
     * @param subject subject that is worked on through the lambda
     * @param func : lambda representing the operation to be done inside the for loop
     */
    private static <T> void invokeOnChildElements(Element elem, T subject, BiConsumer<Element, T> func) {
        NodeList nodes = elem.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                Element subjectElem = ((Element) nodes.item(i));
                func.accept(subjectElem, subject);
            }
        }
    }

    /**
     * @param xmlFile : file to generate interface from
     */
    private static Document getXmlDocument(File xmlFile) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        Document xmlDoc = null;
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            xmlDoc = builder.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        return xmlDoc;
    }
}
