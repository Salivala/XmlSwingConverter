package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ExampleController {
    HashMap<String, ActionListener> actions;
    int k = 0;
    public ExampleController() {
        actions = new HashMap<>();
        actions.put("testAction", e -> {System.out.println("defaultAction");});
        JButton button = new JButton();
        XmlSwingConverter converter = new XmlSwingConverter(Paths.get("./src", "example.xml"), actions);
        converter.addAction("testAction", e-> {
            ((JLabel) converter.namedContainers.get("title")).setText(
                ((JTextField)converter.namedContainers.get("newNameField")).getText());
        });
    }

    public static void main(String[] args) {
        ExampleController controller = new ExampleController();
    }


}
