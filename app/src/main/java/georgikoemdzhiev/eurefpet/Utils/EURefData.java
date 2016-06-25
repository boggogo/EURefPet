package georgikoemdzhiev.eurefpet.Utils;

import java.io.Serializable;

/**
 * Created by koemdzhiev on 25/06/16.
 */
public class EURefData implements Serializable{
    public  String links;
    public  int id;
    public  String type;
    public  EuRefAttr attributes;


    public EURefData(String links, int id, EuRefAttr atr) {
        this.links = links;
        this.id = id;
        attributes = atr;
    }

    public EURefData() {
       this.links = "";
       this.id = 0;
       this.attributes = null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EuRefAttr getAttributes() {
        return attributes;
    }

    public void setAttributes(EuRefAttr attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "EURefData{" +
                "links='" + links + '\'' +
                ", id=" + id +
                ", attributes=" + attributes +
                '}';
    }


}
