package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public abstract class XSLayout {
    HashMap<String, Component> childComponents = new HashMap<>();
    private static int xsLayoutCount = 0;
    abstract JPanel producePanel();


    public static int getXsLayoutCount() {
        return xsLayoutCount;
    }

    public static void setXsLayoutCount(int xsLayoutCount) {
        XSLayout.xsLayoutCount = xsLayoutCount;
    }

    public static void incrementCount() {
        xsLayoutCount++;
    }
}
