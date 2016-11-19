package client.model;

import org.xml.sax.helpers.DefaultHandler;

public class TaskXMLParser extends DefaultHandler{
    String thisElement;
    String status = "";
    String color = "";
    boolean firstTask = true;
    TaskStatusAndColorModel taskStatusAndColorModel = TaskStatusAndColorModel.getInstance();
    @Override
    public void startDocument() {
        System.out.println("Start SAX parser");
    }

    @Override
    public void startElement (String uri, String localName, String qName, org.xml.sax.Attributes attributes)  throws org.xml.sax.SAXException {
        thisElement = qName;
        if (thisElement.equals("task")) {
            if (firstTask) {
                firstTask = false;
                status =  attributes.getValue("mark");
            }
            else {
                taskStatusAndColorModel.put(status, color);
                status =  attributes.getValue("mark");
                color = "";
            }
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName)  throws org.xml.sax.SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws org.xml.sax.SAXException {
        if (thisElement.equals("color")) {
            color = new String(ch, start, length);
        }
    }
    @Override
    public void endDocument() {
        taskStatusAndColorModel.put(status, color);
        System.out.println("Document pars end");
    }
}
