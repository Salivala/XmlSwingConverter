package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.HashMap;

public class ExampleController {
    HashMap<String, ActionListener> actions;
    int k = 0;
    public ExampleController() {
        actions = new HashMap<>();
        actions.put("testAction", e -> {System.out.println("defaultAction");});
        JButton button = new JButton();
        XmlSwingConverter converter = new XmlSwingConverter(Paths.get("./src", "example.xml"), actions);
        converter.addAction("go", e -> {
            ((JLabel) converter.namedContainersAndStrings.get("title")).setText(
                    ((JTextField) converter.namedContainersAndStrings.get("setTitle")).getText()
            );
        });
    }

    public static void main(String[] args) {
        ExampleController controller = new ExampleController();
    }


}
