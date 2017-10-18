
import java.io.File;
import java.util.Hashtable;

public class CommandInterface {

    protected Dataset data, filteredData;
    protected Vocabulary vocabulary;
    protected Rewriter rewriter;
    protected RewritingVector initialRewritingVector, filteredRewritingVector;

    public CommandInterface() {
        this.data = null;
        this.filteredData = null;
        this.vocabulary = null;
        this.rewriter = null;
        this.initialRewritingVector = null;
        this.filteredRewritingVector = null;
    }

    public void displayHelp() {
        System.out.println("Commands available:");
        System.out.println("\t-help: display this help message");
        System.out.println("\t-quit: quit the program");
        System.out.println("\t-loadVoc: load a vocabulary from a csv file");
        System.out.println("\t-loadData: load a dataset from a csv file");
        System.out.println("\t-rewrite: rewrite a dataset wrt. a vocabulary");
        System.out.println("\t-filter: filter the dataset according to a given term");
        System.out.println("\t-associations: discover properties associated to a term used in a previous filter");
        System.out.println("\t-atypicality: discover atypical properties associated to a term used in a previous filter");
        System.out.println("\t-diversity: discover the dimensions offering the most diversed explanations");
    }

    public void prompt() {
        System.out.print("(type help or a command) => ");
    }

    public void showVoc() {
        if (this.vocabulary == null) {
            System.out.println("You first have to load a vocabulary");
        } else {
            System.out.println(this.vocabulary);
        }
    }

    public void loadVoc() {
        File f;
        String path;
        System.out.println("Path of the vocabulary file: ");
        path = System.console().readLine();
        f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            this.vocabulary = new Vocabulary(path);
            try {
                this.vocabulary.loadfile();
                this.rewriter = new Rewriter(this.vocabulary);
                System.out.println(this.vocabulary.getNbTerms() + " terms loaded.");
            } catch (Exception e1) {
                System.err.println("Problem loading the vocabulary");
                System.err.println("Error message " + e1.getMessage());
                e1.printStackTrace();
            }
        }
    }

    public void loadData() {
        File f;
        String path = "", type = "", possibleTypes = "facebook|flight";

        System.out.println("Path of the data file: ");
        path = System.console().readLine();
        f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            try {
                this.data = new Dataset(path);
                System.out.println("Type of the items (" + possibleTypes + "): ");
                type = System.console().readLine();
                if (possibleTypes.contains(type.toLowerCase())) {
                    this.data.loadFile(type);
                    System.out.println(this.data.cardinality() + " items loaded.");
                } else
                    System.out.println("Wrong item type");
            } catch (Exception e) {
                System.err.println("Prb loading the dataset " + path + " with item type " + type);
                System.err.println("Error message " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void rewrite() {
        int err = 0;
        if (this.vocabulary == null) {
            System.err.println("You first have to load a vocabulary");
            err = 1;
        }
        if (this.data == null) {
            System.err.println("You first have to load a dataset");
            err = 1;
        }
        if (err == 0) {
            if (this.initialRewritingVector == null)
                this.initialRewritingVector = this.rewriter.rewriteDataSet(this.data);

            if (this.filteredData != null)
                this.filteredRewritingVector = this.rewriter.rewriteDataSet(this.filteredData);
        }
    }


    public void filter() {
        int filter;
        int err = 0;
        if (this.vocabulary == null) {
            System.err.println("You first have to load a vocabulary");
            err = 1;
        }
        if (this.data == null) {
            System.err.println("You first have to load a dataset");
            err = 1;
        }
        if (err == 0) {
            System.out.print(this.vocabulary);
            System.out.println("Give the id of the filter to apply : ");
            filter = Integer.parseInt(System.console().readLine());
            if (filter >= 1 && filter <= this.vocabulary.getNbTerms()) {
                this.filteredData = this.data.filter(this.vocabulary, filter);
                System.out.println(this.filteredData.cardinality() + " items satisfy filter " + filter);
            }
        }

    }


    //Returns fuzzy sets and their associated dependance degree wrt. the current filter
    public Hashtable<FuzzySet, Double> discoverAssociationRules() {
        Hashtable<FuzzySet, Double> ret = new Hashtable<FuzzySet, Double>();
        int err = 0;
        if (this.filteredData == null) {
            System.err.println("You first have to apply a filter.");
        }
        if (this.data == null) {
            System.err.println("You first have to load a dataset");
            err = 1;
        }
        if (err == 0) {
            System.out.println("Here");
            SpecificityCloud dataobj = new SpecificityCloud(this.data, this.vocabulary, this.rewriter);
            System.out.println(dataobj.displayAssociations());
            // ret = dataobj.displayAssociations();
            // need to start work from here
        }

        System.out.println(" it is your turn");
        return ret;
    }

    //Returns fuzzy sets and their associated atypicality degree wrt. the current filtered dataset
    public Hashtable<FuzzySet, Double> discoverAtypicalProperties() {
        Hashtable<FuzzySet, Double> ret = new Hashtable<FuzzySet, Double>();
        int err = 0;
        if (this.filteredData == null) {
            System.err.println("You first have to apply a filter.");
        }
        if (this.data == null) {
            System.err.println("You first have to load a dataset");
            err = 1;
        }
        if (err == 0) {
            System.out.println("Here");
            // need to start work from here
        }
        System.out.println(" it is your turn");
        return ret;
    }


    //Returns attributes and their associated diversity degree
    public Hashtable<Attribute, Double> discoverDiversifiedDimensions() {
        Hashtable<Attribute, Double> ret = new Hashtable<Attribute, Double>();
        int err = 0;
        if (this.filteredData == null) {
            System.err.println("You first have to apply a filter.");
        }
        if (this.data == null) {
            System.err.println("You first have to load a dataset");
            err = 1;
        }
        if (err == 0) {
            System.out.println("Here");
            // need to start work from here
        }

        System.out.println(" it is your turn");
        return ret;
    }

    public void run() {
        String line = " ";

        while (!line.toLowerCase().equals("quit")) {
            prompt();
            line = System.console().readLine().toLowerCase().trim();
            switch (line) {
                case "help":
                    displayHelp();
                    break;
                case "loadvoc":
                    loadVoc();
                    break;
                case "showvoc":
                    showVoc();
                    break;
                case "loaddata":
                    loadData();
                    rewrite();
                    System.out.println(this.initialRewritingVector);
                    break;
                case "filter":
                    filter();
                    rewrite();
                    System.out.println(this.filteredRewritingVector);
                    break;
                case "associations":
                    discoverAssociationRules();
                    break;
                case "atypicality":
                    discoverAtypicalProperties();
                    break;
                case "diversity":
                    discoverDiversifiedDimensions();
                    break;
                case "quit":
                    System.out.println("Bye...");
                    break;
                default:
                    System.out.println("!Command Unknown!");
            }
        }

    }

}
