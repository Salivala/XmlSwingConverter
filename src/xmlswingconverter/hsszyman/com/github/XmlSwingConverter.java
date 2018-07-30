package xmlswingconverter.hsszyman.com.github;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.naming.ConfigurationException;
import javax.security.sasl.SaslException;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        String title = xmlDoc.getDocumentElement().getAttribute("title");
        XmlSwingPage xsp = new XmlSwingPage(title);
        Element root = xmlDoc.getDocumentElement();
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                xsp.setLayout(getLayout((Element) child));
            }
        }
        return xsp;
    }

    private BorderLayout parseXSBorderLayout(Element elem) {
        BorderLayout layout = new BorderLayout();
        JPanel layoutsPanel = new JPanel();
        for (int i = 0; i < elem.getChildNodes().getLength() ; i++) {
            Node node = elem.getChildNodes().item(i);
            if (node instanceof Element) {
                Element childElem = (Element) node;
                if (childElem.getTagName().equals("BorderLayout.TOP"))
                    layout.addLayoutComponent();
            }
        }

        return layout;
    }

    private FlowLayout parseXSFlowLayout(Element elem) {
        return new FlowLayout();
    }

    private boolean isLayout(Element elem) {
        String elemTag = elem.getTagName();
        return elemTag.equals("BorderLayout") || elemTag.equals("FlowLayout");
    }

    private LayoutManager getLayout(Element elem) {
        if (elem.getTagName().equals("BorderLayout"))
            return parseXSBorderLayout(elem);
        else
            return parseXSFlowLayout(elem);
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
