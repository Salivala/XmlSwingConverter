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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * TODO: Respect encapsulation and provide setters and getters for fields
 */
public class XmlSwingConverter {
    private DocumentBuilderFactory builderFactory;
    private DocumentBuilder builder;
    private Document xmlDoc;

    private Map<String, ActionListener> actions;

    /**
     * Component maps, for easy access to generated components
     */
    private Map<String, JButton> buttons = new HashMap<>();
    private Map<String, JLabel> labels = new HashMap<>();
    private Map<String, JTextField> textFields = new HashMap<>();
    private Map<String, JList> lists = new HashMap<>();
    private Map<String, JTree> trees = new HashMap<>();


    private JFrame frame;
    private Path path;

    /**
     * @param xmlPath : path of the xml file that is formatted as an XMLSwingPage
     * @param actions : hashmap of actionListeners
     */
    public XmlSwingConverter(Path xmlPath, Map<String, ActionListener> actions) {
        this.path = xmlPath;
        this.actions = actions;
        File xmlFile = xmlPath.toFile();
        Thread createJFrameThread = new Thread(() -> {
            frame = new JFrame();
            initXmlHandlingFields(xmlFile);
            frame.setContentPane(parseElementAsContainer((Element) xmlDoc.getDocumentElement().getChildNodes().item(1)));
            configureFrame();
        });
        createJFrameThread.start();
        try {
            createJFrameThread.join();
            frame.setVisible(true);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public XmlSwingConverter(File file, Map<String, ActionListener> actions) {
    }

    /**
     * Configure JFrame using common defaults and XML Attributes from parent node
     */
    private void configureFrame() {
        String frameSize = xmlDoc.getDocumentElement().getAttribute("size");

        if (!frameSize.equals("")) {
            int x, y;
            x = Integer.parseInt(frameSize.substring(0, frameSize.indexOf("x")));
            y = Integer.parseInt(frameSize.substring(frameSize.indexOf("x") + 1), frameSize.length());
            frame.setSize(x,y);
        }
        frame.setTitle(xmlDoc.getDocumentElement().getAttribute("title"));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
            case "BoxLayout":
                return getBoxLayoutPanel(currentParentElem);
            case "JButton":
                return getJButton(currentParentElem);
            case "JLabel":
                return getJLabel(currentParentElem);
            case "JTextField":
                return getJTextField(currentParentElem);
            case "JList":
                return getJList(currentParentElem);
            case "JTree":
                return getJTree(currentParentElem);
            default:
                return new JLabel("oh no");
        }
    }

    /**
     * @param elem
     * @return
     */
    private JTree getJTree(Element elem) {
        JTree tree = new JTree(getTreeNode(elem, new DefaultMutableTreeNode(elem.getAttribute("title"))));
        trees.put(elem.getAttribute("name"), tree);
        return tree;
    }

    private DefaultMutableTreeNode getTreeNode(Element elem, DefaultMutableTreeNode node) {
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
    private JTextField getJTextField(Element elem) {
        Text textNode = (Text) elem.getFirstChild();
        JTextField field = new JTextField();
        field.setText(textNode.getData().trim());
        String textFieldColumns = elem.getAttribute("columns");
        if (!textFieldColumns.equals("")) {
            field.setColumns(Integer.valueOf(textFieldColumns));
        }

        textFields.put(elem.getAttribute("name"), field);
        return field;
    }


    /**
     * @param elem : Current element in the DOM
     * @return a new JList
     */
    private JList getJList(Element elem) {
        JList<String> list = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        list.setModel(listModel);
        lists.put(elem.getAttribute("name"), list);
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
    private JLabel getJLabel(Element elem) {
        Text textNode = (Text) elem.getFirstChild();
        JLabel label = new JLabel(textNode.getData().trim());
        labels.put(elem.getAttribute("name"), label);
        return label;
    }

    /**
     * @param elem : current element
     * @return button that was produced
     */
    private JButton getJButton(Element elem) {
        JButton button = new JButton();
        String methodName = elem.getAttribute("action");
        Text textNode = (Text) elem.getFirstChild();
        button.setText(textNode.getData().trim());
        button.addActionListener(actions.get(methodName));
        button.setVisible(true);
        buttons.put(elem.getAttribute("name"), button);
        return button;
    }

    /**
     *
     * @param currentParentElem : current Element in the dom
     * @return new Jpanel container a border layout
     */
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
            else if (currElem.getTagName().endsWith("CENTER"))
                currContainer.add(parseElementAsContainer((Element) currElem.getChildNodes().item(1)), BorderLayout.CENTER);
        });
        return panel;
    }

    private JPanel getFlowLayoutPanel(Element currentParentElem) {
        JPanel panel = new JPanel();
        FlowLayout layout = new FlowLayout();
        panel.setLayout(new FlowLayout());
        invokeOnChildElements(currentParentElem, panel, (elem, currContainer) -> {
            currContainer.add(parseElementAsContainer(elem));
        });
        return panel;
    }

    private JPanel getBoxLayoutPanel(Element currentParentElem) {
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
            currPanel.add(parseElementAsContainer(currElem));
        });

        return panel;
    }

    /**
     * Helper method to reduce redundant code
     * @param elem element to get the child elements of
     * @param container container that elements can be added to
     * @param func : lambda representing the operation to be done inside the for loop
     */
    private void invokeOnChildElements(Element elem, Container container, ChildElemIterator func) {
        NodeList nodes = elem.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                Element subjectElem = ((Element) nodes.item(i));
                func.elemIteratorMethod(subjectElem, container);
            }
        }
    }

    private void invokeOnChildElements(Element elem, DefaultListModel<String> list, ChildListModelIterator func) {
        NodeList nodes = elem.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                Element subjectElem = ((Element) nodes.item(i));
                func.elemChildIteratorMethod(subjectElem, list);
            }
        }
    }

    private void invokeOnChildElements (Element elem, DefaultMutableTreeNode treeNode, TreeNodeChildIterator func) {
        NodeList nodes = elem.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                Element subjectElem = ((Element) nodes.item(i));
                func.elemTreeNodeChildIterator(subjectElem, treeNode);
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

    public JButton getButton(String buttonName) {
        return buttons.get(buttonName);
    }

    public void setButton(String buttonName, JButton button) {
        buttons.put(buttonName, button);
    }

    public JLabel getLabel(String labelName) {
        return labels.get(labelName);
    }

    public void setLabel(String labelName, JLabel label) {
        labels.put(labelName, label);
    }

    public JTextField getTextField(String textFieldName) {
        return textFields.get(textFieldName);
    }

    public void setTextFields(String textFieldName, JTextField textField) {
        textFields.put(textFieldName, textField);
    }

    public JTree getTree(String treeName) {
        return trees.get(treeName);
    }

    public void setTree(String treeName, JTree tree) {
        trees.put(treeName, tree);
    }

    public JList getList(String listName) {
        return lists.get(listName);
    }

    public void setList(String listName, JList list) {
        lists.put(listName, list);
    }
}
