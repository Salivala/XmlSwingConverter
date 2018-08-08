package xmlswingpagefactory;

import xmlswingpagefactory.interfaces.JListGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines a Mutable class that holds references to all Swing components using seperate Maps devided by Swing Componenet type
 * May contain helper methods for configuring the referenced swing frame, as well as other convenience methods
 */
public class XmlSwingPage {

    private JFrame frame;

    /**
     * Maps that contain terminal Swing Components (components that cannot contain other components)
     */
    private Map<String, JButton> buttonMap = new HashMap<>();
    private Map<String, JTextField> textFieldMap = new HashMap<>();
    private Map<String, JTextArea> textAreaMap = new HashMap<>();
    private Map<String, JLabel> labelMap = new HashMap<>();
    private Map<String, JComboBox<String>> comboBoxMap = new HashMap<>();
    private Map<String, JList<String>> listMap = new HashMap<>();

    /**
     * Maps that contain non-terminal Swing Components (components that can contain other components)
     */
    private Map<String, JPanel> panelMap = new HashMap<>();

    XmlSwingPage() {
        frame = new JFrame();
    }

    public void setComboBoxModel(String comboBoxName, List<String> strList) {
        JComboBox<String> box = comboBoxMap.get(comboBoxName);
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        for (String str : strList) {
            boxModel.addElement(str);
        }
        box.setModel(boxModel);
    }

    public void setButton(String buttonName, JButton button) {
        buttonMap.put(buttonName, button);
    }
    public JButton getButton(String buttonName) {
        return buttonMap.get(buttonName);
    }
    public void setTextField(String textFieldName, JTextField textField) {
        textFieldMap.put(textFieldName, textField);
    }
    public JTextField getTextField(String textFieldName) {
        return textFieldMap.get(textFieldName);
    }
    public void setTextArea(String textAreaName, JTextArea textArea) {
        textAreaMap.put(textAreaName, textArea);
    }
    public JTextArea getTextArea(String textAreaName) {
        return textAreaMap.get(textAreaName);
    }
    public void setLabel(String labelName, JLabel label) {
        labelMap.put(labelName, label);
    }
    public JLabel getLabel(String labelName) {
        return labelMap.get(labelName);
    }
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public void setComboBox(String comboBoxName, JComboBox<String> combobox) {
        comboBoxMap.put(comboBoxName, combobox);
    }
    public JComboBox<String> getComboBox(String comboBoxName) {
        return comboBoxMap.get(comboBoxName);
    }
    public void setPanel(String panelName, JPanel panel) {
        this.panelMap.put(panelName, panel);
    }
    public JPanel getPanel(String panelname) {
        return this.panelMap.get(panelname);
    }
    public void setList(String listName, JList<String> list) {
        listMap.put(listName, list);
    }
    public JList<String> getList(String listName) {
        return listMap.get(listName);
    }
}
