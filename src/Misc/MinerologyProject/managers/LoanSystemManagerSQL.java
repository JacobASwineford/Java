package Misc.MinerologyProject.managers;

import Misc.MinerologyProject.Loan;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Class that implements the <code> LoanSystemManager </code> interface.
 * This class is used to add, update, or delete loans from the database.
 *
 * This class currently supports Rock, Mineral and Fossil as Specimen.
 *
 * @author Jacob Swineford
 */
public class LoanSystemManagerSQL implements LoanSystemManager {

    /**
     * Adds a loan to the database using a <code> Loan </code>
     * object.
     *
     * @param loan A <code> Loan </code> object which is used to
     *                 add a new loan to the database.
     * @return The <code> Loan </code> object that was recently added
     *         added to the database, and <code> null </code> if it could
     *         not be added.
     */
    @Override
    public Loan addLoan(Loan loan) {
        return null;
    }

    /**
     * Updates a loan in the database using a <code> Loan </code>
     * object.
     *
     * @param loan A <code> Loan </code> object that is expected
     *                 to carry the latest information for this loan.
     * @return A <code> Loan </code> object with the latest information,
     *         and <code> null </code> if it could not be updated.
     */
    @Override
    public Loan updateLoan(Loan loan) {
        return null;
    }

    /**
     * Deletes a loan from the database using a <code> Loan </code>
     * object. In practice, this will mean deleting a loan
     * based on it's id.
     *
     * @param loan A <code> Loan </code> object which is expected to
     *                 delete from the database.
     * @return The <code> Loan </code> object that was recently deleted,
     *         and <code> null </code> if it could not be deleted.
     */
    @Override
    public Loan deleteLoan(Loan loan) {
        return null;
    }

    /**
     * Gets all the loans in the database, represented by a collection
     * of <code> Loan </code> objects.
     *
     * @return <code> Collection </code> of <code> LOan </code> objects.
     */
    @Override
    public Collection<Loan> getAllLoans() {
        return new LinkedList<>();
    }
}
