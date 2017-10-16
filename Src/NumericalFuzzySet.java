

/**
 * Created by toan on 08/02/17.
 */
public class NumericalFuzzySet extends FuzzySet {
    private double minSupport, minCore, maxCore, maxSupport;

    /**
     * @param i
     * @param label
     * @param p
     * @param minSupport
     * @param minCore
     * @param maxCore
     * @param maxSupport
     */
    public NumericalFuzzySet(int i, String label, int p, double minSupport, double minCore, double maxCore, double maxSupport) {
        super(i, label, p);
        this.minSupport = minSupport;
        this.minCore = minCore;
        this.maxCore = maxCore;
        this.maxSupport = maxSupport;
    }


    /**
     * @return
     */
    public String toString() {
        return "(" + id + ")" + "Numerical<Fy=uzzySet {" + "minSupport = " + this.minSupport + "\t minCore = " + this.minCore + "\t maxCore = " + this.maxCore + "\t maxSupport = " + this.maxSupport + '}';
    }


    /**
     * @param s
     * @returnx
     */
    public Double getMu(String s) {

        // Always set up the value is equal to zero or null in order to return it at the end of the method.

        Double result = 0.0, v = null;
        if ((s != null) && (s.indexOf("NA") == -1) && (s.indexOf("null") == -1)) {
            try {

                v = Double.parseDouble((String) s);
                if (v < this.minSupport || v > this.maxSupport) {
                    result = 0.0;
                } else if (v >= this.minCore && v <= this.maxCore) {
                    result = 1.0;
                } else {
                    if (v < this.minCore) {
                        result = ((v - this.minSupport) / (this.minCore - this.minSupport));
                    } else {
                        result = ((v - this.maxSupport) / (this.maxCore - this.maxSupport));

                    }
                }

            } catch (NumberFormatException e) {
                System.err.println("Impossible to translate " + s + "into double");
                System.err.println("Error message " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e1) {
                System.err.println("Impossible to process the value " + s);
                System.err.println("Error message " + e1.getMessage());
                e1.printStackTrace();
            }
        }
        //System.out.println("mu("+this.label+","+v+")="+result+"\n");
        return result;
    }

    // Create Getter

    /**
     * @return
     */
    public double getMinSupport() {
        return minSupport;
    }

    public double getMinCore() {
        return minCore;
    }

    public double getMaxCore() {
        return maxCore;
    }

    public double getMaxSupport() {
        return maxSupport;
    }


    // Create Setter


    public void setMinSupport(double minSupport) {
        this.minSupport = minSupport;
    }

    public void setMinCore(double minCore) {
        this.minCore = minCore;
    }

    public void setMaxCore(double maxCore) {
        this.maxCore = maxCore;
    }

    public void setMaxSupport(float maxSupport) {
        this.maxSupport = maxSupport;
    }
}
