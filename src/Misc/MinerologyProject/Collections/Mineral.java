package Misc.MinerologyProject.Collections;

import java.util.Objects;

/**
 * Class used to represent a mineral. A mineral
 * is one of the geological collections that is kept track of
 * in the database.
 *
 * @author Jacob Swineford
 */
public class Mineral extends Specimen {

    // General properties
    private String name;
    private String chemicalFormula;
    private String danaClassification;
    private String crystalFormula;
    private String crystalHabit;

    /**
     * Gets the name of this <code> Mineral </code>
     *
     * @return the name of this <code> Mineral </code>
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this <code> Mineral </code>
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the chemical formula of this <code> Mineral </code>
     *
     * @return the chemical formula of this <code> Mineral </code>
     */
    public String getChemicalFormula() {
        return chemicalFormula;
    }

    /**
     * Sets the chemical formula of this <code> Mineral </code>
     */
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }

    /**
     * Gets the Dana classification of this <code> Mineral </code>
     *
     * @return the Dana classification of this <code> Mineral </code>
     */
    public String getDanaClassification() {
        return danaClassification;
    }

    /**
     * Sets the Dana classification of this <code> Mineral </code>
     */
    public void setDanaClassification(String danaClassification) {
        this.danaClassification = danaClassification;
    }

    /**
     * Gets the crystal formula of this <code> Mineral </code>
     *
     * @return the crystal formula of this <code> Mineral </code>
     */
    public String getCrystalFormula() {
        return crystalFormula;
    }

    /**
     * Sets the crystal formula of this <code> Mineral </code>
     */
    public void setCrystalFormula(String crystalFormula) {
        this.crystalFormula = crystalFormula;
    }

    /**
     * Gets the crystal habit of this <code> Mineral </code>
     *
     * @return the crystal habit of this <code> Mineral </code>
     */
    public String getCrystalHabit() {
        return crystalHabit;
    }

    /**
     * Sets the crystal habit of this <code> Mineral </code>
     */
    public void setCrystalHabit(String crystalHabit) {
        this.crystalHabit = crystalHabit;
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
                name, chemicalFormula, danaClassification, crystalFormula, crystalHabit);
    }
}
