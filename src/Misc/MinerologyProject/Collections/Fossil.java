package Misc.MinerologyProject.Collections;

import java.util.Objects;

/**
 * Class used to represent a fossil. A fossil
 * is one of the geological collections that is kept track of
 * in the database.
 *
 * @author Jacob Swineford
 */
public class Fossil extends Specimen {

    // General properties
    private String genus;
    private String species;
    private String clade1;
    private String clade2;
    private String clade3;
    private String clade4;
    private int age;
    private String formation;
    private String member;

    /**
     * Gets the genus of this <code> Fossil </code>.
     *
     * @return the Genus of this <code> Fossil </code>
     */
    public String getGenus() {
        return genus;
    }

    /**
     * Sets the genus of this <code> Fossil </code>.
     */
    public void setGenus(String genus) {
        this.genus = genus;
    }

    /**
     * Gets the species of this <code> Fossil </code>.
     *
     * @return the species of this <code> Fossil </code>
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets the species of this <code> Fossil </code>.
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * Gets the first clade grouping of this <code> Fossil </code>.
     *
     * @return the first clade grouping of this <code> Fossil </code>
     */
    public String getClade1() {
        return clade1;
    }

    /**
     * Sets the first clade grouping of this <code> Fossil </code>.
     */
    public void setClade1(String clade1) {
        this.clade1 = clade1;
    }

    /**
     * Gets the second clade grouping of this <code> Fossil </code>.
     *
     * @return the second clade grouping of this <code> Fossil </code>
     */
    public String getClade2() {
        return clade2;
    }

    /**
     * Sets the second clade grouping of this <code> Fossil </code>.
     */
    public void setClade2(String clade2) {
        this.clade2 = clade2;
    }

    /**
     * Gets the third clade grouping of this <code> Fossil </code>.
     *
     * @return the third clade grouping of this <code> Fossil </code>
     */
    public String getClade3() {
        return clade3;
    }

    /**
     * Sets the third clade grouping of this <code> Fossil </code>.
     */
    public void setClade3(String clade3) {
        this.clade3 = clade3;
    }

    /**
     * Gets the fourth clade grouping of this <code> Fossil </code>.
     *
     * @return the fourth clade grouping if this <code> Fossil </code>
     */
    public String getClade4() {
        return clade4;
    }

    /**
     * Sets the fourth clade grouping of this <code> Fossil </code>.
     */
    public void setClade4(String clade4) {
        this.clade4 = clade4;
    }

    /**
     * Gets the age of this <code> Fossil </code>.
     *
     * @return the age of this <code> Fossil </code>
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of this <code> Fossil </code>.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the formation of this <code> Fossil </code>.
     *
     * @return the formation if this <code> Fossil </code>
     */
    public String getFormation() {
        return formation;
    }

    /**
     * Sets the formation of this <code> Fossil </code>.
     */
    public void setFormation(String formation) {
        this.formation = formation;
    }

    /**
     * Gets the member of this <code> Fossil </code>.
     *
     * @return the member of this <code> Fossil </code>
     */
    public String getMember() {
        return member;
    }

    /**
     * Sets the member of this <code> Fossil </code>.
     */
    public void setMember(String member) {
        this.member = member;
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
                genus, species, clade1, clade2, clade3, clade4, age, formation, member);
    }
}
