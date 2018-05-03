package datacore.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CDN {
    private List<ElementCDN> element = new ArrayList<ElementCDN>();

    public CDN() {}

    public CDN(List<ElementCDN> element) {
        this.element = element;
    }

    public List<ElementCDN> getElement() {
        return element;
    }
    public ElementCDN getElement(int idx) {
        return this.element.get(idx);
    }

    public void setElement(ElementCDN element){
        this.element.add(element);
    }
    public void setElement(List<ElementCDN> element) {
        this.element = element;
    }
    public void setElement(String name, String sourceUrl, String type, String lastupdate){
        this.element.add(new ElementCDN(name,sourceUrl,type,lastupdate));
    }
}
