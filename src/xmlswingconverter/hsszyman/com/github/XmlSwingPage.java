package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class XmlSwingPage {
    private JPanel jpanel = new JPanel();
    private JFrame frame;

    public void setActions(Map<String, ActionListener> actions) {
        this.actions = actions;
    }

    private Map<String, ActionListener> actions = new HashMap<>();
    private Map<String, JButton> buttons = new HashMap<>();
    private Map<String, JLabel> labels = new HashMap<>();
    private Map<String, JTextField> textFields = new HashMap<>();
    private Map<String, JList> lists = new HashMap<>();
    private Map<String, JTree> trees = new HashMap<>();

    XmlSwingPage() {
        frame = new JFrame();
    }

    void setLayout(LayoutManager layout) {
        jpanel.setLayout(layout);
    }

    public boolean setVisible() {
        if (frame != null) {
            frame.setVisible(true);
            return true;
        }
        return false;
    }

    public void setTitle(String title) {
        frame.setTitle(title);
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

    public void setAction(String actionName, ActionListener action) {
        actions.put(actionName, action);
    }

    public ActionListener getAction(String actionName) {
        return actions.get(actionName);
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
