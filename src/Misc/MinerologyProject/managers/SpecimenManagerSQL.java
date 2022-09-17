package Misc.MinerologyProject.managers;

import Misc.MinerologyProject.Collections.Fossil;
import Misc.MinerologyProject.Collections.Mineral;
import Misc.MinerologyProject.Collections.Rock;
import Misc.MinerologyProject.Collections.Specimen;
import Misc.MinerologyProject.Filters;
import Misc.MinerologyProject.Utility.SpecimenUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Class that implements the <code> SpecimenManager </code> interface.
 * This class is used to add, update, or delete specimens from the database.
 *
 * This class currently supports <code> Rock </code>, <code> Mineral </code>,
 * and <code> Fossil </code> as types of <code> Specimen </code>, following
 * that they also have database table counter-parts.
 *
 * @author Jacob Swineford
 */
public class SpecimenManagerSQL implements SpecimenManager {

    /**
     * Adds a specimen to the database using a <code> Specimen </code>
     * object.
     *
     * @param specimen A <code> Specimen </code> object which is used to
     *                 add a new specimen to the database.
     * @return The <code> Specimen </code> object that was recently added
     *         added to the database, and <code> null </code> if it could
     *         not be added.
     */
    @Override
    public Specimen addSpecimen(Specimen specimen) {
        Specimen spec = null;
        String sql;
        Connection c = null;
        PreparedStatement p;
        try {
            if (specimen instanceof Rock) {
                Rock rock = (Rock) specimen;
                sql = "INSERT INTO Rock VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?);";
                p = c.prepareStatement(sql);
                p.setInt(1, rock.getSpecimenNumber());
                p.setString(2, rock.getName());
                p.setString(3, rock.getType());
                p.setString(4, rock.getPresentStructure());
                p.setInt(5, rock.getAge());
                p.setString(6, rock.getFormation());
                p.setString(7, rock.getMember());
                p.setString(8, rock.getSuite());
                p.setString(9, rock.getThinSection());
                p.executeUpdate();
                spec = rock;
            } else if (specimen instanceof Mineral) {
                Mineral mineral = (Mineral) specimen;
                sql = "INSERT INTO Mineral VALUES (?, ?, ?, ?, ?, ?);";
                p = c.prepareStatement(sql);
                p.setInt(1, mineral.getSpecimenNumber());
                p.setString(2, mineral.getName());
                p.setString(3, mineral.getChemicalFormula());
                p.setString(4, mineral.getDanaClassification());
                p.setString(5, mineral.getCrystalFormula());
                p.setString(6, mineral.getCrystalHabit());
                p.executeUpdate();
                spec = mineral;
            } else if (specimen instanceof Fossil) { // fossil
                Fossil fossil = (Fossil) specimen;
                sql = "INSERT INTO Mineral VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                p = c.prepareStatement(sql);
                p.setInt(1, fossil.getSpecimenNumber());
                p.setString(2, fossil.getSpecies());
                p.setString(3, fossil.getGenus());
                p.setString(4, fossil.getClade1());
                p.setString(5, fossil.getClade2());
                p.setString(6, fossil.getClade3());
                p.setString(7, fossil.getClade4());
                p.setInt(8, fossil.getAge());
                p.setString(9, fossil.getFormation());
                p.setString(10, fossil.getMember());
                p.executeUpdate();
                spec = fossil;
            }
            // insert into Specimen table
            sql = "INSERT INTO Specimen VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            if (spec != null) { // won't be
                p = c.prepareStatement(sql);
                p.setInt(1, spec.getSpecimenNumber());
                p.setString(2, spec.getCollection());
                p.setString(3, spec.getLocality());
                p.setString(4, spec.getCollector());
                p.setString(5, spec.getPhotoPath());
                p.setString(6, spec.getLocation());
                p.setString(7, spec.getCondition());
                p.setString(8, spec.getAdditionalNotes());
                p.setString(9, spec.getLastModified().toString());
                p.setInt(10, spec.getBloomuNo());
                p.executeUpdate();
            }
            // log, close
        } catch (SQLException e) {
            // log, close connections, close statement
        }
        return spec;
    }

    /**
     * Updates a specimen in the database using a <code> Specimen </code>
     * object.
     *
     * @param specimen A <code> Specimen </code> object that is expected
     *                 to carry the latest information for this specimen.
     * @return A <code> Specimen </code> object with the latest information,
     *         and <code> null </code> if it could not be updated.
     */
    @Override
    public Specimen updateSpecimen(Specimen specimen) {
        Specimen spec = null;
        String sql;
        Connection c = null;
        PreparedStatement p;
        try {
            if (specimen instanceof Rock) {
                Rock rock = (Rock) specimen;
                sql = "UPDATE Rock SET specimen_no = ?, rock_name = ?," +
                        " rock_type = ?, structures = ?, age = ?, formation = ?, member = ?," +
                        " suite = ?, thin_section = ?;";
                p = c.prepareStatement(sql);
                p.setInt(1, rock.getSpecimenNumber());
                p.setString(2, rock.getName());
                p.setString(3, rock.getType());
                p.setString(4, rock.getPresentStructure());
                p.setInt(5, rock.getAge());
                p.setString(6, rock.getFormation());
                p.setString(7, rock.getMember());
                p.setString(8, rock.getSuite());
                p.setString(9, rock.getThinSection());
                p.executeUpdate();
                spec = rock;
            } else if (specimen instanceof Mineral) {
                Mineral mineral = (Mineral) specimen;
                sql = "UPDATE Mineral SET specimen_no = ?, mineral_name = ?," +
                        "chemical_formula = ?, dana_class = ?, crystal_formula = ?, crystal_habit = ? ;";
                p = c.prepareStatement(sql);
                p.setInt(1, mineral.getSpecimenNumber());
                p.setString(2, mineral.getName());
                p.setString(3, mineral.getChemicalFormula());
                p.setString(4, mineral.getDanaClassification());
                p.setString(5, mineral.getCrystalFormula());
                p.setString(6, mineral.getCrystalHabit());
                p.executeUpdate();
                spec = mineral;
            } else if (specimen instanceof Fossil) {
                Fossil fossil = (Fossil) specimen;
                sql = "UPDATE Fossil SET specimen_no = ?, species = ?, genus = ?, higher_clade_1 = ?, " +
                        "higher_clade_2 = ?, higher_clade_3 = ?, higher_clade_4 = ?, age = ?, " +
                        "formation = ?, member = ?;";
                p = c.prepareStatement(sql);
                p.setInt(1, fossil.getSpecimenNumber());
                p.setString(2, fossil.getSpecies());
                p.setString(3, fossil.getGenus());
                p.setString(4, fossil.getClade1());
                p.setString(5, fossil.getClade2());
                p.setString(6, fossil.getClade3());
                p.setString(7, fossil.getClade4());
                p.setInt(8, fossil.getAge());
                p.setString(9, fossil.getFormation());
                p.setString(10, fossil.getMember());
                spec = fossil;
            }
            // update Specimen table
            sql = "UPDATE Specimen SET specimen_no = ?, collection = ?, locality_found = ?, collector_doner_name = ?," +
                    " photo = ?, current_location = ?, condition = ?, notes = ?, last_modified = ?, bloomu_no = ?;";
            if (spec != null) { // won't be
                p = c.prepareStatement(sql);
                p.setInt(1, spec.getSpecimenNumber());
                p.setString(2, spec.getCollection());
                p.setString(3, spec.getLocality());
                p.setString(4, spec.getCollector());
                p.setString(5, spec.getPhotoPath());
                p.setString(6, spec.getLocation());
                p.setString(7, spec.getCondition());
                p.setString(8, spec.getAdditionalNotes());
                p.setString(9, spec.getLastModified().toString());
                p.setInt(10, spec.getBloomuNo());
                p.executeUpdate();
            }
            // log, close
        } catch (SQLException e) {
            // log, close
        }
        return null;
    }

    /**
     * Deletes a specimen from the database using a <code> Specimen </code>
     * object. In practice, this will mean deleting a specimen
     * based on it's id.
     *
     * @param specimen A <code> Specimen </code> object which is expected to
     *                 delete from the database.
     * @return The <code> Specimen </code> object that was recently deleted,
     *         and <code> null </code> if it could not be deleted.
     */
    @Override
    public Specimen deleteSpecimen(Specimen specimen) {
        String sql = "";
        Connection c = null;
        try {
            if (specimen instanceof Rock) {
                sql = "DELETE FROM Rock WHERE specimen_no = ?";
            } else if (specimen instanceof Mineral) {
                sql = "DELETE FROM Mineral WHERE specimen_no = ?";
            } else if (specimen instanceof Fossil) {
                sql = "DELETE FROM Fossil WHERE specimen_no = ?";
            }
            c.prepareStatement(sql).executeUpdate();
            // delete from Specimen
            sql = "DELETE FROM Specimen WHERE specimen_no = ?";
            c.prepareStatement(sql).executeUpdate();
            // close
        } catch (SQLException e) {
            // log
        }
        return null;
    }

    /**
     * Gets all the specimen in the database, represented by a collection
     * of <code> Specimen </code> objects.
     *
     * @return <code> Collection </code> of <code> Specimen </code> objects.
     */
    @Override
    public Collection<Specimen> getAllSpecimen() {
        Collection<Specimen> specimens = new LinkedList<>(getAllOf(new Rock()));
        specimens.addAll(getAllOf(new Mineral()));
        specimens.addAll(getAllOf(new Fossil()));
        return specimens;
    }

    /**
     * Gets all of any particular child of <code> Specimen </code>. Currently,
     * This method supports the children <code> Rock </code>, <code> Mineral </code>
     * and <code> Fossil </code>.
     *
     * @param collectionType Type of child to be queried
     * @return A <code> Collection </code> of all records of a particular child.
     */
    public Collection<Specimen> getAllOf(Specimen collectionType) {
        Collection<Specimen> col = new LinkedList<>();
        String sql = "";
        if (collectionType instanceof Rock) {
            sql = "SELECT * FROM Rock INNER JOIN Specimen ON" +
                    "Specimen.specimen_no = Rock.specimen_no;";
        } else if (collectionType instanceof Mineral) {
            sql = "SELECT * FROM Mineral INNER JOIN Specimen ON" +
                    "Specimen.specimen_no = Mineral.specimen_no;";
        } else if (collectionType instanceof Fossil) {
            sql = "SELECT * FROM Fossil INNER JOIN Specimen ON" +
                    "Specimen.specimen_no = Fossil.specimen_no;";
        }
        Connection c = null;
        ResultSet rs;
        PreparedStatement p;
        try {
            p = c.prepareStatement(sql);
            rs = p.executeQuery();
            if (rs == null) {
                // close connections
                return col;
            }

            while (rs.next()) {
                if (collectionType instanceof Rock) {
                    Rock r = SpecimenUtility.getRockFromResultSet(rs);
                    col.add(r);
                } else if (collectionType instanceof Mineral) {
                    Mineral m = SpecimenUtility.getMineralFromResultSet(rs);
                    col.add(m);
                } else if (collectionType instanceof Fossil) {
                    Fossil f = SpecimenUtility.getFossilFromResultSet(rs);
                    col.add(f);
                }
            }

        } catch (SQLException e) {
            // log
        }
        // close
        return col;
    }

    /**
     * Searches the database for <code> Specimen </code> objects
     * that match with the filter variables in the given
     * <code> Filter </code> object.
     *
     * @param filters given <code> Filter </code> object, configured
     *                with filter variables from a
     *                <code> HttpRequest </code> object.
     * @return <code> Collection </code> of <code> Specimen </code>
     *         objects that match the given filter variables.
     */
    @Override
    public Collection<Specimen> filteredSearch(Filters filters) {
        Collection<Specimen> col = new HashSet<>();
        String sql = "SELECT * FROM name WHERE student_name LIKE '%a%'";
        return null;
    }
}
