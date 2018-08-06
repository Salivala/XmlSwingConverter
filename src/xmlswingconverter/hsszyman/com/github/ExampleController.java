package xmlswingconverter.hsszyman.com.github;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

import static xmlswingconverter.hsszyman.com.github.XmlSwingConverter.getNewXmlSwingPage;
import static java.nio.file.Paths.get;

public class ExampleController {
    HashMap<String, ActionListener> actions;
    int k = 0;
    private ExampleController() {
        actions = new HashMap<>();
        actions.put("do", e -> {System.out.println("Example action");});
        XmlSwingPage page = getNewXmlSwingPage(get("./src", "Example2.xml"), actions);
    }

    public static void main(String[] args) {
        ExampleController controller = new ExampleController();
        //Stream<Double> testStream = Stream.generate(Math::random);
        //testStream.filter(e -> e > 0.99999).forEach(System.out::println);
        //List<String> l = Stream.of("dlwakjd", "Dalkjdldwakj").limit(1).collect(groupingBy((e, i)) -> );
        //Stream<BigInteger> l = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        //List<BigInteger> o = l.filter(e -> e.isProbablePrime(100)).limit(100).collect(());
        //System.out.print(o.toString());
    }

}
