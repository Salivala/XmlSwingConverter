package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.naming.ConfigurationException;
import javax.security.sasl.SaslException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class XmlSwingConverter {
    private DocumentBuilderFactory builderFactory;
    private DocumentBuilder builder;
    private Document xmlDoc;
    private XmlSwingPage page;

    public static void main(String[] args) {
        XmlSwingConverter converter = new XmlSwingConverter(Paths.get("./src", "example.xml"), new HashMap<>());
    }

    /**
     * @param xmlPath : path of the xml file that is formatted as an XMLSwingPage
     * @param actions : hashmap of actionListeners
     */
    public XmlSwingConverter(Path xmlPath, HashMap<String, ActionListener> actions) {
        File xmlFile = xmlPath.toFile();
        initXmlHandlingFields(xmlFile);
        XmlSwingPage page = newXmlSwingPage();
    }

    private XmlSwingPage newXmlSwingPage() {
        String title = xmlDoc.getDocumentElement().getAttribute("title");
        System.out.println(title);
        return new XmlSwingPage(title);
    }

    /**
     */
    private void initXmlHandlingFields(File xmlFile) {
        builderFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = builderFactory.newDocumentBuilder();
            xmlDoc = builder.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
