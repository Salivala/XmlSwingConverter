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
        actions.put("do", e -> {System.out.println("Example action");});
        XmlSwingConverter converter = new XmlSwingConverter(Paths.get("./src", "Example1.xml"), actions);
        converter.buttons.get("button").addActionListener(e -> {
            converter.labels.get("title").setText("weeee");
        });
    }

    public static void main(String[] args) {
        ExampleController controller = new ExampleController();
    }


}
