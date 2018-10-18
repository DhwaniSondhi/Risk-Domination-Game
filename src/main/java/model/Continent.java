package model;

import java.util.ArrayList;

/**
 * Continent Model
 */
public class Continent {
    public int id;
    public String name;
    public ArrayList<Country> countries;
    public int controlValue;

    /**
     * Initializes the id, name and countrolvalue of continent
     *
     * @param id           id of continent
     * @param name         name of continent
     * @param controlValue control value of continent
     */
    public Continent(int id, String name, int controlValue) {
        this.id = id;
        this.name = name;
        this.controlValue = controlValue;
        countries = new ArrayList<>();
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return name + "|" + countries.size();
    }
}
