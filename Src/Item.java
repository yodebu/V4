

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by toan on 15/03/17.
 */
abstract public class Item {
    protected Hashtable<String, String> rawDescription;
    protected Double muin;


    // Create a constructor
    public Item() {
        this.rawDescription = new Hashtable<String, String>();
        this.muin = 1.0;
    }

    public Double getMuIn() {
        return this.muin;
    }

    public void setMuIn(Double f) {
        this.muin = f;
    }

    public void setMuInAnd(Double f) {
        this.muin = Math.min(this.muin, f);
    }

    public void setMuInOr(Double f) {
        this.muin = Math.max(this.muin, f);
    }

    // Create Getter and Setter
    public Hashtable<String, String> getRawDescription() {
        return rawDescription;
    }

    public String toString() {
        String r = "{";
        for (String key : this.rawDescription.keySet()) {
            r += key + ":" + this.rawDescription.get(key) + "; ";
        }
        return r + "}";
    }

    public String getRawValue(String a) {
        String ret = null;
        if (this.rawDescription.containsKey(a)) {
            ret = this.rawDescription.get(a);
        }
        return ret;

    }

    abstract public void cutRawDescription(String[] c) throws Exception;
}
