package Misc.MinerologyProject.Collections;

import java.util.Objects;

/**
 * Class used to represent a rock. A rock
 * is one of the geological collections that is kept track of
 * in the database.
 *
 * @author Jacob Swineford
 */
public class Rock extends Specimen {

    // General properties
    private String name;
    private String type;
    private String presentStructure;
    private int age;
    private String formation;
    private String member;
    private String suite;
    private String thinSection;

    /**
     * Gets the name of this <code> Rock </code>.
     *
     * @return the name of this <code> Rock </code>
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this <code> Rock </code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of this <code> Rock </code>.
     *
     * @return the type of this <code> Rock </code>
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of this <code> Rock </code>.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the present structure of this <code> Rock </code>.
     *
     * @return the present structure of this <code> Rock </code>
     */
    public String getPresentStructure() {
        return presentStructure;
    }

    /**
     * Sets the present structure of this <code> Rock </code>.
     */
    public void setPresentStructure(String presentStructure) {
        this.presentStructure = presentStructure;
    }

    /**
     * Gets the age of this <code> Rock </code>.
     *
     * @return the age of this <code> Rock </code>
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of this <code> Rock </code>.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the formation of this <code> Rock </code>.
     *
     * @return the formation of this <code> Rock </code>
     */
    public String getFormation() {
        return formation;
    }

    /**
     * Sets the formation of this <code> Rock </code>.
     */
    public void setFormation(String formation) {
        this.formation = formation;
    }

    /**
     * Gets the member of this <code> Rock </code>.
     *
     * @return the member of this <code> Rock </code>
     */
    public String getMember() {
        return member;
    }

    /**
     * Sets the member of this <code> Rock </code>.
     */
    public void setMember(String member) {
        this.member = member;
    }

    /**
     * Gets the suite of this <code> Rock </code>.
     *
     * @return the suite of this <code> Rock </code>
     */
    public String getSuite() {
        return suite;
    }

    /**
     * Sets the suite of this <code> Rock </code>.
     */
    public void setSuite(String suite) {
        this.suite = suite;
    }

    /**
     * Gets the thin section of this <code> Rock </code>.
     *
     * @return the thin section of this <code> Rock </code>
     */
    public String getThinSection() {
        return thinSection;
    }

    /**
     * Sets the thin section of this <code> Rock </code>.
     */
    public void setThinSection(String thinSection) {
        this.thinSection = thinSection;
    }

    /**
     * Gets the hashcode for this object, based on this class'
     * variables and it's parent class' variables.
     *
     * @return the hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                specimenNumber, collection, locality, collector, photoPath, lastModified, bloomuNo, location, condition, additionalNotes,
                name, type, presentStructure, age, formation, member, suite, thinSection);
    }
}
