package georgikoemdzhiev.eurefpet.Utils;

/**
 * Created by koemdzhiev on 25/06/16.
 */
public class EURefConstituency {
    public String name;
    public String ons_code;
    public String mp;
    public int signature_count;

    public EURefConstituency() {
        this.name = "";
        this.ons_code = "";
        this.mp = "";
        this.signature_count = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOns_code() {
        return ons_code;
    }

    public void setOns_code(String ons_code) {
        this.ons_code = ons_code;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public int getSignature_count() {
        return signature_count;
    }

    public void setSignature_count(int signature_count) {
        this.signature_count = signature_count;
    }

    @Override
    public String toString() {
        return "EURefConstituency{" +
                "name='" + name + '\'' +
                ", ons_code='" + ons_code + '\'' +
                ", mp='" + mp + '\'' +
                ", signature_count=" + signature_count +
                '}';
    }
}
