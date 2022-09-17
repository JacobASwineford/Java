package Misc.MinerologyProject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class used to represent a loan. A loan is
 * put into the database to signal that any particular
 * specimen has been "loaned" out to someone else
 * for use over any particular span of time.
 *
 * @author Jacob Swineford
 */
public class Loan {

    private int specimenNumber;
    private LocalDateTime dateStart;
    private LocalDateTime dateStop;
    private int bloomuNo;
    private String locationTo;
    private String reason;

    /**
     * Gets the specimen number for this <code> Loan </code>.
     *
     * @return the specimen number for this <code> Loan </code>
     */
    public int getSpecimenNumber() {
        return specimenNumber;
    }

    /**
     * Sets the specimen number for this <code> Loan </code>.
     */
    public void setSpecimenNumber(int specimenNumber) {
        this.specimenNumber = specimenNumber;
    }

    /**
     * Gets the starting date for this <code> Loan </code>.
     *
     * @return the starting date for this <code> Loan </code>
     */
    public LocalDateTime getDateStart() {
        return dateStart;
    }

    /**
     * Sets the starting date for this <code> Loan </code>.
     */
    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Gets the stopping date for this <code> Loan </code>.
     *
     * @return the stopping date for this <code> Loan </code>
     */
    public LocalDateTime getDateStop() {
        return dateStop;
    }

    /**
     * Sets the stopping date for this <code> Loan </code>.
     */
    public void setDateStop(LocalDateTime dateStop) {
        this.dateStop = dateStop;
    }

    /**
     * Gets the Bloomu id for this <code> Loan </code>.
     *
     * @return the Bloomu id for this <code> Loan </code>
     */
    public int getBloomuNo() {
        return bloomuNo;
    }

    /**
     * Sets the Bloomu id for this <code> Loan </code>.
     */
    public void setBloomuNo(int bloomuNo) {
        this.bloomuNo = bloomuNo;
    }

    /**
     * Gets the "location to" for this <code> Loan </code>.
     *
     * @return the "location to" for this <code> Loan </code>
     */
    public String getLocationTo() {
        return locationTo;
    }

    /**
     * Sets the "location to" for this <code> Loan </code>.
     */
    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    /**
     * Gets the reason for this <code> Loan </code>.
     *
     * @return the reason for this <code> Loan </code>
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason for this <code> Loan </code>.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Overridden <code> equals </code> method. This method
     * checks if two <code> Loan </code> objects are equal,
     * which translates to them having the same specimen number.
     *
     * @param o Object to test, generally another <code> Loan </code>
     *          object.
     * @return true if both <code> Specimen </code> objects have the
     *         same specimen number, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return specimenNumber == ((Loan) o).specimenNumber;
    }

    /**
     * Gets the hashcode for this object, based on this class'
     * variables.
     *
     * @return the hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(specimenNumber, dateStart, dateStop, bloomuNo, locationTo, reason);
    }
}
