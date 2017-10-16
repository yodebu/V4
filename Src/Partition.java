

import java.util.ArrayList;

/**
 * Created by toan on 08/02/17.
 */
public class Partition {

    private int numberOfFuzzySet;
    private ArrayList<FuzzySet> modalities;//

    // Create the constructor

    public Partition() {
        this.numberOfFuzzySet = 0;
        this.modalities = new ArrayList<FuzzySet>();
    }

    /**
     *
     * @return
     */
    public String toString() {
        String r = "{";
        for (FuzzySet fs : this.modalities) {
            r += fs.getId() + "=>" + fs.getLabel() + " | ";
        }
        return r.substring(0, r.length() - 2) + "}";
    }


    public void printPartition() {
        for (int i = 0; i < this.modalities.size(); i++) {
            System.out.println("Fuzzy Set: " + this.modalities.get(i));
        }
    }

    /**
     * @return
     */
    public int getNumberOfFuzzySet() {
        return numberOfFuzzySet;
    }


    /**
     * @return
     */
    public ArrayList<FuzzySet> getModalities() {
        return this.modalities;
    }

    /**
     *
     * @param mod
     */
    public void addModalities(FuzzySet mod) {
        this.modalities.add(mod);
        this.numberOfFuzzySet++;
    }
}
