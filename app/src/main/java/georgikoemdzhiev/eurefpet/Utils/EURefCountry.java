package georgikoemdzhiev.eurefpet.Utils;

/**
 * Created by koemdzhiev on 25/06/16.
 */
public class EURefCountry {
    public  String name;
    public  String code;
    public  int signature_count;

    public EURefCountry() {
        this.name = "";
        this.code = "";
        this.signature_count = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSignature_count() {
        return signature_count;
    }

    public void setSignature_count(int signature_count) {
        this.signature_count = signature_count;
    }

    @Override
    public String toString() {
        return "EURefCountry{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", signature_count=" + signature_count +
                '}';
    }
}
