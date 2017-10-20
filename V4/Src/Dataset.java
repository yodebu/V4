
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by toan on 15/03/17.
 */


public class Dataset implements Iterable<Item> {

    private String file;
    private ArrayList<Item> items;
//Array to store the different attributeNames

    private ArrayList<String> attributeNames;


    public Dataset(String file) {
        this.file = file;
        this.items = new ArrayList<Item>();
        this.attributeNames = new ArrayList<String>();
    }


    public Dataset filter(Vocabulary v, int filter) {
        Dataset d = new Dataset(this.getFile());
        String attConcerned = v.getAttributeByFSId(filter).getNameOfAttribute();
        Double muv = 0.0;
        d.setAttributeNames(this.getAttributeNames());
        d.items = new ArrayList<Item>();
        int test = 0;
        for (Item i : this.items) {
            muv = v.getFSById(filter).getMu(i.getRawValue(attConcerned));
            i.setMuInAnd(muv);
            if (i.getMuIn() > 0.0) {
                d.addItem(i);
            }
        }
        return d;
    }

    public int cardinality() {
        return this.items.size();
    } // the length of one line.

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setAttributeNames(ArrayList<String> s) {
        attributeNames = s;
    }

    public ArrayList<String> getAttributeNames() {
        return attributeNames;
    }

    public void setAttributeNames(String line) {
        String[] partOfLine = line.split(",");
        for (int i = 0; i < partOfLine.length; i++) {
            this.attributeNames.add(partOfLine[i]);
        }
    }

    public void addItem(Item i) {
        items.add(i);
    }


    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void loadFile(String typeOfItem) throws Exception {
        int counter = 0, nbOfItems = 0;
        Item i;
        BufferedReader CSVFile;
        String row;
        String[] rowCut;

        try {
            CSVFile = new BufferedReader(new FileReader(file));

            row = CSVFile.readLine();

            while (row != null) {
                if (counter == 0) {
                    this.setAttributeNames(row);
                } else {
                    rowCut = row.split(",");
                    if (rowCut.length == this.attributeNames.size()) {
                        try {
                            if (typeOfItem.equals("facebook")) {
                                i = new ItemFacebook();
                            } else if (typeOfItem.equals("flight")) {
                                i = new ItemFlight();
                            } else {
                                System.err.println("Unknown type of object");
                                i = null;
                            }
                            if (i != null) {
                                i.cutRawDescription(rowCut);
                                items.add(i);
                                nbOfItems = nbOfItems + 1;
                            }
                        } catch (Exception e) {
                            System.err.println("Error message " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                counter = counter + 1;
                row = CSVFile.readLine();
            }

            CSVFile.close();
        } catch (IOException e1) {
            System.err.println("Error reading the file " + this.file);
            System.err.println("Error message " + e1.getMessage());
            e1.printStackTrace();
        }
//	  System.out.println("Number of lines: " +counter+" Number of items created: " +nbOfItems);
    }

    public Iterator<Item> iterator() {
        return items.iterator();
    }

    public String toString() {
        String r = "";
        for (Item i : this.items) {
            r += "\t-" + i + "\n";
        }
        return r;
    }

}


