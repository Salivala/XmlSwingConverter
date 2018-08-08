package xmlswingpagefactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;
import java.util.ConcurrentModificationException;

/**
 * Defines a class whose responsibility is to retrieve a path representing an XML file and with the help of delegates
 * Convert the file into an XmlSwingPage holding all of the desired referenced Swing Components
 */
public class XmlSwingPageFactory {
    Document xmlDoc;

    /*
     * Delegates to determine behavior implementations
     */
    private ConverterSuite delegates;

    /**
     *
     * @param xmlFilePath
     */
    XmlSwingPageFactory(Path xmlFilePath) {
        xmlDoc = generateXmlDoc(xmlFilePath);
        delegates = new ConverterSuite.Builder().build();
        //System.out.println(delegates.router.toString());
    }

    /**
     * @return new XmlSwingPage
     */
    XmlSwingPage newXmlSwingPage() {
        XmlSwingPage xsp = new XmlSwingPage();
        Thread generateXmlSwingCompeonents = new Thread(() -> {
            // Get the root element from the document
            Element rootElem = ((Element) xmlDoc.getDocumentElement().getChildNodes().item(1));
            // Set the content pane of the XmlSwingPage to the result of routing the root
            xsp.getFrame().setContentPane(delegates.router.routeToContainer(rootElem, xsp, delegates));
        });
        generateXmlSwingCompeonents.start();
        try {
            generateXmlSwingCompeonents.join();
            xsp.getFrame().setVisible(true);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConcurrentModificationException();
        }
        configureFrame(xsp, xmlDoc);
        return xsp;
    }


    private void configureFrame(XmlSwingPage page, Document xmlDoc) {
        String frameSize = xmlDoc.getDocumentElement().getAttribute("size");
        if (!frameSize.equals("")) {
            int x = Integer.parseInt(frameSize.substring(0, frameSize.indexOf("x")));
            int y = Integer.parseInt(frameSize.substring(frameSize.indexOf("x") + 1), frameSize.length());
            page.getFrame().setSize(x,y);
        }
        page.getFrame().setTitle(xmlDoc.getDocumentElement().getAttribute("title"));
        page.getFrame().setLocationRelativeTo(null);
        page.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        page.getFrame().setVisible(true);
    }
    /**
     * @param xmlFilePath : Path of the xml file
     * @return : document representing xml dom tree
     */
    private Document generateXmlDoc(Path xmlFilePath) {
        File xmlFile = xmlFilePath.toFile();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document xmlDoc;

        // TODO: Consider better exception-handling
        try {
            builder = builderFactory.newDocumentBuilder();
            xmlDoc = builder.parse(xmlFile);
        } catch (Exception e) {
            xmlDoc = null;
            for (Throwable ex : e.getSuppressed()) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return xmlDoc;
    }
}
