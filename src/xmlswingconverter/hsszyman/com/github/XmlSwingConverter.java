package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


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
    public XmlSwingConverter(Path xmlPath, Map<String, ActionListener> actions) {
        File xmlFile = xmlPath.toFile();
        initXmlHandlingFields(xmlFile);
        parseXSP();
    }

    private XmlSwingPage parseXSP() {
        XmlSwingPage xsp = new XmlSwingPage();
        parseLayout((Element) xmlDoc.getDocumentElement().getChildNodes().item(1));
        return xsp;
    }

    LayoutManager parseLayout(Element elem) {
        String elemTagName = elem.getTagName();
        if (elemTagName.equals("BorderLayout")) {
            return parseBorderLayout(elem);
        }
        else if (elemTagName.equals("FlowLayout")) {
            return parseFlowLayout(elem);
        }
        return new BorderLayout();
    }

    private BorderLayout parseBorderLayout(Element elem) {
        BorderLayout borderLayout = new BorderLayout();
        return new BorderLayout();
    }

    FlowLayout parseFlowLayout(Element elem) {
        return new FlowLayout();
    }

    private void parseElement(Element elem, methodInvocation method) {
        NodeList nodes = elem.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i) instanceof Element) {
                method.workOnElement((Element) nodes.item(i));
            }
        }
    }


    /**
     * @param xmlFile : file to generate interface from
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
