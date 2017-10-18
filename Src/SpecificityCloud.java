import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

class WindowParams {
    public final static int W = 1600;
    public final static int H = 1000;
    public final static int Z = 40;
    public final static float FONT_MAX = 50.0f;
    public final static double ALPHA_CARD = 0.1;
    public final static float FONT_MIN = 12.0f;
    public final static Color[] attColors = {new Color(33, 00, 00), new Color(99, 00, 00), new Color(255, 00, 00), new Color(255, 99, 99), new Color(99, 76, 00), new Color(255, 80, 00), new Color(255, 178, 66), new Color(66, 66, 00), new Color(255, 255, 00), new Color(255, 255, 204), new Color(33, 66, 00), new Color(99, 255, 33), new Color(00, 99, 99), new Color(33, 255, 255), new Color(00, 76, 99), new Color(33, 99, 255), new Color(99, 204, 255), new Color(76, 00, 99), new Color(178, 66, 255), new Color(99, 00, 99), new Color(255, 66, 255), new Color(99, 00, 76), new Color(255, 66, 178), new Color(60, 60, 60)};
}


class Term extends JLabel implements MouseListener {
    private double cardRel = 0, specificity = 0;
    private String label = "";
    private SpecificityCloud cloud = null;
    private FuzzySet fuzzySet = null;
    private Attribute attribute = null;

    /**
     * @param a
     * @param f
     * @param cr
     * @param spec
     * @param i
     * @param s
     */
    public Term(Attribute a, FuzzySet f, double cr, double spec, int i, SpecificityCloud s) {
        super(f.getLabel() + " " + a.getNameOfAttribute());
        this.fuzzySet = f;
        this.attribute = a;
        this.label = label;
        this.cardRel = cr;
        this.specificity = spec;
        this.addMouseListener(this);
        this.cloud = s;
        double zAdjusted = WindowParams.Z - (0.9 * i);
        // Zoom factor
        int x = new Double(WindowParams.W / 2.8 + zAdjusted * i * Math.cos(i)).intValue();
        int y = new Double(WindowParams.H / 2.3 + zAdjusted * i * Math.sin(i)).intValue();
        this.setLocation(x, y);

        float fr = WindowParams.FONT_MIN;
        if ((WindowParams.FONT_MAX * cr) > WindowParams.FONT_MIN)
            fr = new Double(WindowParams.FONT_MAX * cr).floatValue();
        this.setFont(this.getFont().deriveFont(fr));
        this.setForeground(WindowParams.attColors[a.getId() % WindowParams.attColors.length]);
        this.setSize((int) fr * 14, (int) fr);

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        this.cloud.filter(this.attribute, this.fuzzySet);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        String data1 = this.cloud.displayAssociations();
        String data2 = this.cloud.displayDiversity();
        String finaldata = "<HTML>" + data1 + "<br>" + data2 + "</HTML>";
        System.out.println(finaldata);
        System.out.println(data1);
        System.out.println(data2);
        this.setToolTipText(finaldata);
    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.setToolTipText(this.cloud.displayDiversity());

    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }
}


public class SpecificityCloud extends JFrame {

    private Vocabulary vocabulary = null;
    private Rewriter rw = null;
    private Dataset data = null;
    private String currentFilter;
    private ArrayList<Integer> filters;
    protected RewritingVector initialRewritingVector, filteredRewritingVector;


    /**
     * @param d
     * @param v
     * @param r
     */

    public SpecificityCloud(Dataset d, Vocabulary v, Rewriter r) {
        this.data = d;
        this.vocabulary = v;
        this.rw = r;
        this.currentFilter = "";
        this.filters = new ArrayList<Integer>();
        this.initialRewritingVector = null;
        this.filteredRewritingVector = null;
    }

    /**
     * @return
     */
/*
    public String displayAssociations() {
        //String ret = "No association rule found";
        String ret = "";
        if ((this.filteredRewritingVector != null) && (!this.currentFilter.equals(""))) {
            //System.out.println("COMPUTE ASSOCIATIONS HERE");
            //RewritingVector rw = new RewritingVector();

            Hashtable<String, Double> assoc = new Hashtable<String, Double>();
            double dep, associationDegree;

            for (Integer i : initialRewritingVector.getVector().keySet()) {
                dep = this.filteredRewritingVector.getMu(i) / this.initialRewritingVector.getMu(i);

                if (dep >1){

                    associationDegree = 1 - 1 / dep;
                    //System.out.println("ass"+associationDegree);
                    System.out.println("The attribute is a property depending on filter label \n");
                } else {
                    associationDegree = 0;
                    System.out.println("The attribute has no dependence or negative dependency with filter label");
                }

                assoc.put(String.valueOf(this.filters), associationDegree);
                String result = ret.concat(assoc.toString());
                System.out.println(result);
                System.out.println("Association degree between filter and this label is:" + associationDegree);

            }
        } else {
            ret = "Association rules can be discovered only if a first filter is applied";
        }
        System.out.println(ret);
        return ret;
    }

*/
    public String displayAssociations() {
        //String ret = "No association rule found";
        String ret = "";
        if ((this.filteredRewritingVector != null) && (!this.currentFilter.equals(""))) {
            System.out.println("COMPUTE ASSOCIATIONS HERE");
            // RewritingVector rw = new RewritingVector();
            Hashtable<String, Double> assoc = new Hashtable<String, Double>();
            double dep, associationDegree;
            for (Integer i : initialRewritingVector.getVector().keySet()) {
                Integer m = vocabulary.getFSById(i).getId();
                String n = "=>" + "[" + m.toString() + "]";
                System.out.println(n);
                dep = this.filteredRewritingVector.getMu(i) / this.initialRewritingVector.getVector().get(i);
                System.out.println("dep:" +dep);
                if (dep > 1) {
                    associationDegree = 1 - 1 / dep;
                    System.out.println("ass"+associationDegree);
                    System.out.println("The attribute is a property depending on filter label \n");
                } else {
                    associationDegree = 0;
                    System.out.println("The attribute has no dependence or negative dependency with filter label");
                }


                String result2 = String.valueOf(this.filters).concat(n);
                assoc.put(result2, associationDegree);
            }
            // assoc.put(String.valueOf(this.filters), associationDegree);
            String result = ret.concat(assoc.toString());
            // System.out.println(result);
            System.out.println(result);


        } else {
            // ret = "Association rules can be discovered only if a first filter is applied";
        }
        System.out.println(ret);
        return ret;
    }



 /*   public String displayAtypicality() {
        String ret = "";
        Hashtable<String, Double> D = new Hashtable<String, Double>();
        if ((this.filteredRewritingVector != null) && (!this.currentFilter.equals(""))) {
            //Vocabulary vocabulary = new Vocabulary();

            FuzzySet fuzzySet = null;
            Partition p = new Partition();
            int p1 = p.getNumberOfFuzzySet();
            for (int j = 0; j < p1; j++) {
                double max = 0, d = 0;
                for (int k = 0; k < p1; k++) {
                    if (fuzzySet.getTerm().equals("Categorical")) {
                        if (j != k) {
                            d = 1;
                        } else {
                            d = 0;
                        }
                    } else if ((fuzzySet.getTerm().equals("Numerical")) && (p1 > 1)) {
                        d = abs(j - k) / (p1 - 1);
                    } else {
                        d = 0;
                    }
                    double temp = min(d, p1);
                    if (max < temp) {
                        max = temp;
                    }
                }
                //  Set<String> labels = coverFilter.keySet();
                // get label also with modalities for k.
                // for (String label : labels) {
                D.put(fuzzySet.getLabel(), max);
            }
// Create method to compute NC
            for (final Integer i : this.filters) {
                final double temp1, double NCScore;
                temp1 = this.filteredRewritingVector.getMu(i) / this.initialRewritingVector.getMu(i);
                NCScore = 1 - min(1, temp1);
                final Hashtable<String, Double> NCComputation = new Hashtable<String, Double>() {

                return NCComputation;
                }
            }
        }

// Create method to compute atypicality

        public Hashtable<String, Float> atypicalityComputation () {
            Hashtable<String, Float> atypical = new Hashtable<String, Float>();
            float atypScore;
            Set<Integer> labels = filteredRewritingVector.getVector().keySet();

            for (int lab : labels) {
                atypScore = min(.get(lab), D.get(lab));
                NC.put(lab, atypScore);
            }
            return atypical;
        }

    }*/

    public String displayDiversity() {
        String ret = "";
        if ((this.filteredRewritingVector != null) && (!this.currentFilter.equals(""))) {
            System.out.print("here here");

            RewritingVector rw = new RewritingVector();
            Hashtable<Integer, Double> div = new Hashtable<Integer, Double>();
            //Vocabulary vocabulary;
            Double sum = 0.0, divScore;
            int X = 0, qi;
            for (Integer i : rw.getVector().keySet()) {
                // cover = rw.getVector().get(i);
                qi = this.vocabulary.getAttributeByFSId(i).getPartition().getNumberOfFuzzySet();

                for (Integer j : rw.getVector().keySet()) {
                    // if ((!i.equals(j)) && (this.vocabulary.getAttributeByFSId(i).getNameOfAttribute().equals(this.vocabulary.getAttributeByFSId(j).getNameOfAttribute()))) {
                    sum += rw.getVector().get(j) - rw.getVector().get(i) / qi;
                    X += 1;
                    divScore = 1 - sqrt(sum / abs(X));
                    System.out.println("therehere : " + div.toString());
                    String result = ret.concat(div.toString());
                    System.out.println("here : " + result);
                    ret = result;
                    div.put(i, divScore);
                    System.out.println("The diversity score degree between filter and this label is:" + div);
                }
            }
        }
        System.out.println("SATA SATA" + ret);

        return ret;
    }



    /**
     * @param rw
     * @return
     */
// Compute Specificity Degree
    public Hashtable<Integer, Double> computeSpecificityDegrees(RewritingVector rw) {
        Hashtable<Integer, Double> specs = new Hashtable<Integer, Double>();
        Double cover = 0.0, R = 0.0, sum;
        int X = 0, qi = 0;
        for (Integer i : rw.getVector().keySet()) {
            cover = rw.getVector().get(i);
            qi = this.vocabulary.getAttributeByFSId(i).getPartition().getNumberOfFuzzySet();
            X = 0;
            sum = 0.0;

            for (Integer j : rw.getVector().keySet()) {
                if ((!i.equals(j)) && (this.vocabulary.getAttributeByFSId(i).getNameOfAttribute().equals(this.vocabulary.getAttributeByFSId(j).getNameOfAttribute()))) {
                    X += 1;
                    sum += (abs((j - rw.getVector().get(j)) - (i - rw.getVector().get(i))) / qi);

                }
            }
            sum /= X;

            specs.put(i, (cover * (1 - sum)));
        }
        return specs;
    }


    /**
     * @param d
     */
    public void setData(Dataset d) {
        this.data = d;
    }

    /**
     * @param a
     * @param f
     */
    public void filter(Attribute a, FuzzySet f) {

        Dataset d = this.data.filter(this.vocabulary, f.getId());
        if (this.currentFilter != "")
            this.currentFilter += " AND ";
        this.currentFilter += a.getNameOfAttribute() + " " + f.getLabel();
        this.filters.add(f.getId());
        this.setData(d);
        this.getContentPane().removeAll();
        this.display();
    }

    /**
     * @param specs
     * @return
     */
    public ArrayList<Integer> sortFSBySpec(Hashtable<Integer, Double> specs) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Integer i = new Integer(0);
        for (Integer idx : specs.keySet()) {
            i = 0;
            while (i < ret.size() && specs.get(idx) < specs.get(ret.get(i))) {
                i++;
            }
            ret.add(i, idx);
        }
        return ret;
    }

    public void display() {
        Term label1;
        double cardRel;
        Hashtable<Integer, Double> specs;
        RewritingVector rwVect = new RewritingVector();
        ArrayList<Integer> sortedKeys;

        JLabel background = new JLabel(new ImageIcon("./back.jpg"));
        this.setContentPane(background);

        rwVect = this.rw.rewriteDataSet(this.data);
        if (this.initialRewritingVector == null)
            this.initialRewritingVector = rwVect;
        else
            this.filteredRewritingVector = rwVect;

        specs = this.computeSpecificityDegrees(rwVect);

        System.out.println("Rewriting Vector:" + rwVect);
        System.out.println("Specificity rewriting Vector:" + specs + "\n");
        sortedKeys = this.sortFSBySpec(specs);
        System.out.println("Keys sorted by specificity:" + sortedKeys);

        this.setTitle("Term cloud: " + this.currentFilter);
        this.setSize(WindowParams.W, WindowParams.H);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int ind = 0;
        for (Integer i : sortedKeys) {//rwVect.getVector().keySet()){
            if (rwVect.getVector().get(i) > WindowParams.ALPHA_CARD) {
                cardRel = rwVect.getVector().get(i);

                label1 = new Term(this.vocabulary.getAttributeByFSId(i), this.vocabulary.getFSById(i), cardRel, 0.0, ind, this);
                background.add(label1);
                ind++;
            }
        }

        this.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        Vocabulary v = new Vocabulary("./VocFile/airFlights.voc");
        v.loadfile();
        Rewriter rewriter = new Rewriter(v);
        Dataset data = new Dataset("./Data/someFlights2008.csv");
        data.loadFile("flight");

        new SpecificityCloud(data, v, rewriter).display();
    }


}
