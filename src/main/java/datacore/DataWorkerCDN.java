package datacore;

import datacore.xml.ElementCDN;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import java.util.HashMap;

public interface DataWorkerCDN {
    void save(String shortNameLink, String rawUrl) throws JAXBException;
    ElementCDN getCDN(String shortNameLink);
    void load();
    HashMap<String, Object> list();
}
