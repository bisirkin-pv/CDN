package datacore.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "element")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementCDN {
    @XmlAttribute
    private String name;
    @XmlElement
    private String sourceUrl;
    private String type;
    private String lastupdate;
    @XmlElement
    private List<ElementCDN> element;

    public ElementCDN(){}

    public ElementCDN(String name, String sourceUrl, String type, String lastupdate) {
        this.name = name;
        this.sourceUrl = sourceUrl;
        this.type = type;
        this.lastupdate = lastupdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
}
