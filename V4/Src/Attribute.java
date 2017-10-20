

/**
 * Created by toan on 08/02/17.
 */
public class Attribute {

    protected String nameOfAttribute;
    protected String typeOfAttribute;
    protected Partition partition;
    protected Integer idOfAttribute;

    /**
     * @param name
     * @param type
     */

    // Create the constructor.
    public Attribute(String name, String type, Integer i) {
        this.nameOfAttribute = name;
        this.typeOfAttribute = type;
        this.idOfAttribute = i;
        this.partition = new Partition();
    }

    // Create the method printAttribute

    public void printAttribute() {

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\nName of attribute is: " + this.nameOfAttribute);
        System.out.println("Type of attribute is: " + this.typeOfAttribute);

        this.partition.printPartition();
    }

    // Getter

    /**
     * @return
     */
    public Integer getId() {
        return this.idOfAttribute;
    }

    /**
     * @return
     */
    public Partition getPartition() {
        return this.partition;
    }

    /**
     * @return
     */
    public String getNameOfAttribute() {

        return this.nameOfAttribute;
    }

    /**
     * @return
     */
    public String getTypeOfAttribute() {

        return this.typeOfAttribute;
    }
}
