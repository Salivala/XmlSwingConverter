package xmlswingpagefactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
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

    /**
     * Maps that contain non-terminal Swing Components (components that can contain other components)
     */
    private Map<String, JPanel> panelMap = new HashMap<>();
    private Map<String, BorderLayout> borderLayoutMap = new HashMap<>();
    private Map<String, FlowLayout> flowLayoutMap = new HashMap<>();


    XmlSwingPage() {
        frame = new JFrame();
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
}
