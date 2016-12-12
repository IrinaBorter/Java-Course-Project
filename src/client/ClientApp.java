package client;

import client.view.AuthorisationDialog;

import client.model.TaskStatusAndColorModel;
import client.model.TaskXMLParser;
import client.view.ErrorWindow;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        TaskStatusAndColorModel taskStatusAndColorModel = TaskStatusAndColorModel.getInstance();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        DefaultHandler taskXmlParser = new TaskXMLParser();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            parser.parse(new File("F:\\_Univer\\3 course\\java\\resources\\tasks.xml"), taskXmlParser);
        } catch (IOException e) {
            new ErrorWindow("Отсутствует файл tasks.xml");
            e.printStackTrace();
            System.exit(0);
        }
        AuthorisationDialog authDlg = new AuthorisationDialog();
    }
}
