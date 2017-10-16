import java.util.Hashtable;


class RewritingVector {

    protected Hashtable<Integer, Double> vectorI;

    public RewritingVector() {
        vectorI = new Hashtable<Integer, Double>();
    }


    public Hashtable<Integer, Double> getVector() {
        return this.vectorI;
    }

    public void setRW(Integer termid, Double mu) {
        this.vectorI.put(termid, mu);
    }

    public void updateVector(Integer termid, Double mu) {
        if (this.vectorI.containsKey(termid))
            this.vectorI.put(termid, mu + this.vectorI.get(termid));
        else
            this.vectorI.put(termid, mu);

    }

    public void normalize(int i) {
        for (Integer a : this.vectorI.keySet()) {
            this.vectorI.put(a, this.vectorI.get(a) / i);
        }
    }

    public Double getMu(Integer id) {
        return this.vectorI.get(id);
    }

    public String toString() {
        String r = "{";
        for (Integer a : this.vectorI.keySet()) {
            r += a + "=>" + this.vectorI.get(a) + ";";
        }
        return r + "}";
    }

}


class ItemBinaryRewritingVector {

    protected char[] vectorI;

    public ItemBinaryRewritingVector(int size) {
        vectorI = new char[size];
    }

    /**
     * @return
     */
    public char[] getVector() {
        return this.vectorI;
    }

    /**
     * @param termid
     * @param mu
     * @param alpha
     */
    public void updateVector(Integer termid, Double mu, double alpha) {
        if ((alpha == 0.0 && mu > 0.0) || (alpha > 0.0 && mu >= alpha))
            this.vectorI[termid] = '1';
        else
            this.vectorI[termid] = '0';
    }

    /**
     * @param id
     * @return
     */
    public char getMu(Integer id) {
        return this.vectorI[id];
    }

    /**
     * @return
     */
    public String toString() {

        return new String(vectorI);
    }

}

public class Rewriter {

    protected Vocabulary voc;

    public Rewriter(Vocabulary v) {
        this.voc = v;
    }


    //Rewrite and item and returns its rewriting vector
    public RewritingVector rewriteItem(Item i) {
        RewritingVector trw = new RewritingVector();
        String v;
        Double m = 0.0;
        for (String a : this.voc.getAttributesList().keySet()) {//For each attribute
            for (FuzzySet fs : this.voc.getAttributesList().get(a).getPartition().getModalities()){  //For each FS of the partition associated to the attribute concerned
                v = i.getRawValue(this.voc.getAttributesList().get(a).getNameOfAttribute());
                if (v != null) {
                    m = fs.getMu(v);
                }
                trw.setRW(fs.getId(), Math.min(m, i.getMuIn()));
            }
        }
        return trw;
    }

    //Return the normalized datasetRewriting vector

    /**
     * @param d
     * @return
     */
    public RewritingVector rewriteDataSet(Dataset d) {
        RewritingVector r = new RewritingVector();
        RewritingVector itr;
        int card = 0;
        for (Item i : d) {
            itr = this.rewriteItem(i);
            if (itr != null) {
                card++;
                for (Integer termid : itr.getVector().keySet()) {
                    r.updateVector(termid, itr.getVector().get(termid));
                }
            }
        }
        r.normalize(card);
        return r;
    }

    /**
     * @param i
     * @param alpha
     * @return
     */
    public ItemBinaryRewritingVector binaryRewriteItem(Item i, double alpha) {
        ItemBinaryRewritingVector trw = new ItemBinaryRewritingVector(this.voc.getNbTerms());
        String v;
        Double m = 0.0;
        for (String a : this.voc.getAttributesList().keySet()) {//For each attribute
            for (FuzzySet fs : this.voc.getAttributesList().get(a).getPartition().getModalities()) {//For each FS of the partition associated to the attribute concerned
                v = i.getRawValue(this.voc.getAttributesList().get(a).getNameOfAttribute());

                if (v != null) {
                    m = fs.getMu(v);
                }
                trw.updateVector(fs.getId(), m, alpha);
            }
        }
        return trw;
    }
}