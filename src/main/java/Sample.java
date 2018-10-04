import java.util.HashSet;


/**
 * Sample class
 */
public class Sample {

    /**
     * Set of sample texts
     */
    public HashSet<String> mySet;

    /**
     * Constructor
     */
    public Sample() {
        mySet = new HashSet<>();
    }

    public Sample(HashSet<String> mySet) {
        this.mySet = mySet;
    }

    /**
     * format the value and insert into the set
     * <p>Required format is: "value ({lengthOfText})"</p>
     *
     * @param value text to be added to set after formatting
     * @return true if inserted to set else false
     */
    public boolean addToMySet(String value) {
        if (value == null) return false;

        String input = value + " (" + value.length() + ")";
        return mySet.add(input);
    }

    /**
     * remove the value from the set
     *
     * @param value text to be found and removed
     * @return true if value was found in the set. else false
     */
    public boolean removeFromMySet(String value) {
        if (value == null) return false;

        String input = value + " (" + value.length() + ")";
        return mySet.remove(input);
    }

    /**
     * @return the size of the set
     */
    public int getSize() {
        return mySet.size();
    }
}
