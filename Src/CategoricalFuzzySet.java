

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Created by toan on 09/02/17.
 */

public class CategoricalFuzzySet extends FuzzySet {

    private Hashtable<String, Double> descriptionOfCategoricalSet;

    /**
     * @param label
     */
    public CategoricalFuzzySet(int i, String label, int p, String rawDescription) {
        super(i, label, p);
        descriptionOfCategoricalSet = new Hashtable<String, Double>();
        try {
            this.loadFromDescription(rawDescription);
        } catch (FuzzyVocabularyFormatException e) {
            System.err.println("Impossible to parse the raw description for fuzzy set " + label + "(" + rawDescription + ")");
            System.err.println("Error message " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param rawDescription
     * @throws FuzzyVocabularyFormatException
     */

    public void loadFromDescription(String rawDescription) throws FuzzyVocabularyFormatException {
        // Cut each part of the raw description by using the method split.
        String[] partsOfDescription = rawDescription.split(";");
        //System.out.println("Raw description is: " + rawDescription);

        try {
            for (String partOfDescription : partsOfDescription) {
                // Cut each part of the partOfDescription by using the method split.
                String[] smallPartOfDescription = partOfDescription.split(":");
                // System.out.println(smallPartOfDescription[1]);
                String cat = "";
                double degree = 0.0;

                if (smallPartOfDescription.length == 2) {
                    // Access the small parts of description and put them into a Hash Table
                    cat = smallPartOfDescription[0];
                    degree = Double.parseDouble(smallPartOfDescription[1]);
                    this.descriptionOfCategoricalSet.put(cat, degree);
                    //System.out.println("des"+descriptionOfCategoricalSet);

                } else {
                    throw new FuzzyVocabularyFormatException("Wrong description of a categorical fuzzy set " + rawDescription);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Impossible to translate a string into float");
            System.err.println("Error message " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param s
     * @return
     */

    public Double getMu(String s) {
        Double result = 0.0;

        // Check the description of Categorical set contains key or not, if it contains; we return the value of the key, can be used for the line 5 of algorithm.
        if (this.descriptionOfCategoricalSet.containsKey(s)) {
            result = this.descriptionOfCategoricalSet.get(s);
        }
        return result;
    }

    /**
     * @return
     */

    public String toString() {
        String lab = "(" + id + ")" + label + ": ";
        for (Enumeration<String> e = this.descriptionOfCategoricalSet.keys(); e.hasMoreElements(); ) {
            String k = e.nextElement();
            double v = this.descriptionOfCategoricalSet.get(k);
            lab = lab + k + "=>" + v + " ";
        }
        return lab;

    }

}
