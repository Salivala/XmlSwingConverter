package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ExampleController {
    HashMap<String, ActionListener> actions;
    public ExampleController() {
        actions = new HashMap<>();
        JButton button = new JButton();
        actions.put("doThing",
                e -> System.out.println("thing")
                );
    }


}
