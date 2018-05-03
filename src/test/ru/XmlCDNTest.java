package ru;

import datacore.UrlWorker;
import datacore.XmlCDN;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlCDNTest {
    @Test
    public void testCSSExtensionFromRawUrl(){
        String path = "https://raw.githubusercontent.com/bisirkin-pv/DigitalKeyboard/master/DigitalKeyboard.css";
        assertEquals(UrlWorker.getExtension(path),"css");
    }

    @Test
    public void testTypeCSSRequest(){
        String path = "https://raw.githubusercontent.com/bisirkin-pv/DigitalKeyboard/master/DigitalKeyboard.Css";
        String exstension = UrlWorker.getExtension(path);
        assertEquals(UrlWorker.getTypeRequest(exstension),"text/css");
    }
    @Test
    public void testTypeJsRequest(){
        String path = "https://raw.githubusercontent.com/bisirkin-pv/DigitalKeyboard/master/DigitalKeyboard.JS";
        String exstension = UrlWorker.getExtension(path);
        assertEquals(UrlWorker.getTypeRequest(exstension),"text/javascript");
    }
    @Test
    public void testTypeJsonRequest(){
        String path = "https://raw.githubusercontent.com/bisirkin-pv/DigitalKeyboard/master/DigitalKeyboard.json";
        String exstension = UrlWorker.getExtension(path);
        assertEquals(UrlWorker.getTypeRequest(exstension),"application/json");
    }

    @Test
    public void testCreateFile(){
        XmlCDN xmlCDN = new XmlCDN();
        xmlCDN.save("test","test.css");
        File file = new File("cdn.xml");
        assertTrue(file.exists() && file.isFile());

    }
}
