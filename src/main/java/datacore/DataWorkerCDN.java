package datacore;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

public interface DataWorkerCDN {
    void save(String shortNameLink, String rawUrl) throws JAXBException;
    void getCDN();
}
