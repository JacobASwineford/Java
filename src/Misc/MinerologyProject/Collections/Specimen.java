package Misc.MinerologyProject.Collections;

import java.time.LocalDateTime;

/**
 * Class used to represent a Geological Collection.
 * In this application, it is used to group together
 * the geological collection classes <code> Fossil </code>,
 * <code> Mineral </code>, and <code> Rock </code>.
 *
 * <code> Specimen </code> must be abstract to prevent from
 * being directly instantiated, thus losing collections'
 * different types of data.
 *
 * @author Jacob Swineford
 */
public abstract class Specimen {

    // common fields of all collections
    protected int specimenNumber;
    protected String collection;
    protected String locality;
    protected String collector;
    protected String photoPath;
    protected LocalDateTime lastModified;
    protected int bloomuNo;

    // Faculty, Administrator
    protected String location;
    protected String condition;
    protected String additionalNotes;

    /**
     * Gets the specimen number of this geological specimen.
     */
    public int getSpecimenNumber() {
        return specimenNumber;
    }

    /**
     * Sets the specimen number of this geological specimen.
     */
    public void setSpecimenNumber(int specimenNumber) {
        this.specimenNumber = specimenNumber;
    }

    /**
     * gets the collection of this geological specimen.
     */
    public String getCollection() {
        return collection;
    }

    /**
     * Sets the collection of this geological specimen.
     */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    /**
     * Gets the locality of this geological specimen.
     */
    public String getLocality() {
        return locality;
    }

    /**
     * Sets the locality of this geological specimen.
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * Gets the collector of this geological specimen.
     */
    public String getCollector() {
        return collector;
    }

    /**
     * Sets the collector of this geological specimen.
     */
    public void setCollector(String collector) {
        this.collector = collector;
    }

    /**
     * Gets the photo path of this geological specimen.
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * Sets the photo path of this geological specimen.
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    /**
     * Gets the last modified string of this geological specimen.
     */
    public LocalDateTime getLastModified() {
        return lastModified;
    }

    /**
     * Sets the last modified string of this geological specimen.
     */
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets the Bloomu id of this geological specimen.
     */
    public int getBloomuNo() {
        return bloomuNo;
    }

    /**
     * Sets the Bloomu id of this geological specimen.
     */
    public void setBloomuNo(int bloomuNo) {
        this.bloomuNo = bloomuNo;
    }

    /**
     * Gets the location of this geological specimen.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of this geological specimen.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the condition of this geological specimen.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the condition of this geological specimen.
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Gets an administrator or faculty member's
     * notes of this geological specimen.
     */
    public String getAdditionalNotes() {
        return additionalNotes;
    }

    /**
     * Sets an administrator or faculty member's
     * notes of this geological specimen.
     */
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    /**
     * Overridden <code> equals </code> method. This method
     * checks if two <code> Specimen </code> objects are equal,
     * which translates to them having the same specimen number.
     *
     * @param o Object to test, generally another <code> Specimen </code>
     *          object.
     * @return true if both <code> Specimen </code> objects have the
     *         same specimen number, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return specimenNumber == ((Specimen) o).specimenNumber;
    }
}
