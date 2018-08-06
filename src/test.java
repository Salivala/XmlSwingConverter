import xmlswingconverter.hsszyman.com.github.XmlSwingPage;

import java.nio.file.Paths;
import java.util.HashMap;

import static xmlswingconverter.hsszyman.com.github.XmlSwingConverter.*;

public class test {
    public static void main(String[] args) {
        XmlSwingPage page = getNewXmlSwingPage(Paths.get("srs"), new HashMap<>());
    }
}
