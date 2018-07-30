package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class XSLayout {
    Map<String, Component> childComponents = new HashMap<>();
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
