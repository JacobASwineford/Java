package Misc.MinerologyProject.managers;

import Misc.MinerologyProject.Loan;

import java.util.Collection;

/**
 * This <code> Interface </code> specifies the allowable
 * operations on loans in the database.
 *
 * TBH I don't know what a loan is in this context
 *
 * @author Jacob Swineford
 */
public interface LoanSystemManager {

    public Loan addLoan(Loan loan);
    public Loan updateLoan(Loan loan);
    public Loan deleteLoan(Loan loan);
    public Collection<Loan> getAllLoans();

}
