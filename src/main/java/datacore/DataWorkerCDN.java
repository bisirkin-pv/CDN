package datacore;

import datacore.xml.ElementCDN;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public interface DataWorkerCDN {
    void save(String shortNameLink, String rawUrl) throws JAXBException;
    ElementCDN getCDN(String shortNameLink);
    void load();
    boolean delete(String shortNameLink);
    HashMap<String, Object> list();
}
