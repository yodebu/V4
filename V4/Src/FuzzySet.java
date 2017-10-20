

/**
 * Created by toan on 08/02/17.
 */
abstract public class FuzzySet {
    protected String term;
    protected String label;
    protected int id, posInPartition;

    public FuzzySet(int i, String l, int p) {
        this.id = i;
        this.label = l;
        this.posInPartition = p;
    }

    /**
     * @return
     */
    public String getLabel() {

        return this.label;
    }

    public String getTerm(){
        return this.term;
    }

    public int getPositionInPartition() {
        return this.posInPartition;
    }

    public int getId() {
        return this.id;
    }


    /**
     * @param n
     */
    public void setLabel(String n) {

        this.label = n;
    }

    /**
     * @param s
     * @return
     */
    abstract public Double getMu(String s);
}
