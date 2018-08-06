package xmlswingpagefactory;

import java.nio.file.Paths;

public class test {
    public static void main(String[] args) {
        ConverterSuite l = new ConverterSuite.Builder().routerDel(new DefaultTagRouter()).build();
        XmlSwingPageFactory factory = new XmlSwingPageFactory(Paths.get("./src", "Example1.xml"));
        XmlSwingPage page = factory.newXmlSwingPage();
    }
}
