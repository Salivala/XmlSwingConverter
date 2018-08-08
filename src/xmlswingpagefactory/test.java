package xmlswingpagefactory;

import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {
    public static void main(String[] args) {
        ConverterSuite l = new ConverterSuite.Builder().routerDel(new DefaultTagRouter()).build();
        XmlSwingPageFactory factory = new XmlSwingPageFactory(Paths.get("./src", "Example1.xml"));
        XmlSwingPage page = factory.newXmlSwingPage();
        List<String> times = Stream.iterate(
                BigInteger.ZERO, (i) -> i.add(BigInteger.ONE)).map(BigInteger::toString).limit(60).collect(Collectors.toList()
        );
        page.setComboBoxModel("hours", times);
        page.setComboBoxModel("minutes", times);
        page.setComboBoxModel("seconds", times);
    }
}
