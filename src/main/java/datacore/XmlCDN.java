package datacore;

import datacore.xml.CDN;
import datacore.xml.ElementCDN;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Класс работает с xml
 */
public class XmlCDN implements DataWorkerCDN{
    private final String path = "";
    private final String fileNname = "cdn.xml";
    private CDN storage = new CDN();
    @Override
    public void save(String shortNameLink, String rawUrl) throws JAXBException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ElementCDN elementCDN = new ElementCDN(
                 shortNameLink
                ,rawUrl
                ,UrlWorker.getTypeRequest(UrlWorker.getExtension(rawUrl))
                ,dateFormat.format(date));
        storage.setElement(elementCDN);


        JAXBContext context = JAXBContext.newInstance(CDN.class);
        StringWriter writer = new StringWriter();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT
                    , Boolean.TRUE);
        marshaller.marshal(storage, writer);

        try (FileOutputStream flStream = new FileOutputStream(fileNname)) {
            marshaller.marshal(storage, flStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ElementCDN getCDN(String shortNameLink) {
        List<ElementCDN> elements = storage.getElement();
        for(ElementCDN element : elements){
            if(shortNameLink.equals(element.getName())){
                return element;
            }
        }
        return new ElementCDN();
    }

    @Override
    public void load(){
        try {
            JAXBContext context = JAXBContext.newInstance(CDN.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File xml = new File(fileNname);
            storage = (CDN)unmarshaller.unmarshal(xml);
            System.out.println("load success");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Object> list() {
        HashMap<String, Object> list =  new HashMap<>();
        List<List<String>> listElements = new ArrayList<>();
        List<ElementCDN> elementsCDN = storage.getElement();
        for(ElementCDN elementCDN: elementsCDN){
            List<String> element = new ArrayList<>();
            element.add(elementCDN.getName());
            element.add(elementCDN.getType());
            element.add(elementCDN.getSourceUrl());
            listElements.add(element);
        }
        list.put("cdnlist", listElements);
        return list;
    }
}
