package Misc.MinerologyProject.managers;

import Misc.MinerologyProject.Collections.Specimen;
import Misc.MinerologyProject.Filters;

import java.sql.ResultSet;
import java.util.Collection;

/**
 * This <code> Interface </code> specifies the allowable
 * operations on different specimens in the database.
 * Currently, there are three types of specimens supported:
 * Rock
 * Mineral
 * Fossil
 *
 * @author Jacob Swineford
 */
public interface SpecimenManager {

    public Specimen addSpecimen(Specimen specimen);
    public Specimen updateSpecimen(Specimen specimen);
    public Specimen deleteSpecimen(Specimen specimen);
    public Collection<Specimen> getAllSpecimen();
    public Collection<Specimen> filteredSearch(Filters filters);

}
