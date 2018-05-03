package datacore;

import datacore.xml.CDN;
import datacore.xml.ElementCDN;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс работает с xml
 */
public class XmlCDN implements DataWorkerCDN{
    private final String path = "";
    private final String fileNname = "cdn.xml";
    @Override
    public void save(String shortNameLink, String rawUrl) throws JAXBException {
        CDN cdn = new CDN();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ElementCDN elementCDN = new ElementCDN(
                 shortNameLink
                ,rawUrl
                ,UrlWorker.getTypeRequest(UrlWorker.getExtension(rawUrl))
                ,dateFormat.format(date));
        cdn.setElement(elementCDN);


            JAXBContext context = JAXBContext.newInstance(CDN.class);
            StringWriter writer = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT
                    , Boolean.TRUE);
            marshaller.marshal(cdn, writer);

            try (FileOutputStream flStream = new FileOutputStream(fileNname)) {
                marshaller.marshal(cdn, flStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void getCDN() {

    }
}
