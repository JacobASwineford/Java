package Misc.MinerologyProject.Utility;

import Misc.MinerologyProject.Collections.Fossil;
import Misc.MinerologyProject.Collections.Mineral;
import Misc.MinerologyProject.Collections.Rock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Holder for utility methods. add elsewhere.
 *
 * @author Jacob Swineford
 */
public class SpecimenUtility {

    /**
     * Gets a <code> Mineral </code> object from the record
     * that is currently being pointed to in a <code> ResultSet </code>.
     *
     * @param rs given ResultSet
     * @return <code> Mineral </code> object, using information from
     *         the given <code> ResultSet </code>.
     */
    public static Mineral getMineralFromResultSet(ResultSet rs) {
        Mineral m = new Mineral();
        try {
            m.setSpecimenNumber(rs.getInt("specimen_no"));
            m.setCollection(rs.getString("collection"));
            m.setLocality(rs.getString("locality_found"));
            m.setCollector(rs.getString("collector_donor_name"));
            m.setPhotoPath(rs.getString("photo"));
            m.setLocation(rs.getString("current_location"));
            m.setCondition(rs.getString("condition"));
            m.setAdditionalNotes(rs.getString("notes"));
            LocalDateTime now = LocalDateTime.parse(rs.getString("last-modified"));
            m.setLastModified(now);
            m.setBloomuNo(rs.getInt("bloomu_no"));
            m.setName(rs.getString("mineral_name"));
            m.setChemicalFormula(rs.getString("chemical_formula"));
            m.setDanaClassification("dana_class");
            m.setCrystalFormula(rs.getString("crystal_formula"));
            m.setCrystalHabit(rs.getString("habit"));
        } catch (SQLException e) {
            // log
            return null;
        }
        return m;
    }

    /**
     * Gets a <code> Rock </code> object from the record
     * that is currently being pointed to in a <code> ResultSet </code>.
     *
     * @param rs given ResultSet
     * @return <code> Rock </code> object, using information from
     *         the given <code> ResultSet </code>.
     */
    public static Rock getRockFromResultSet(ResultSet rs) {
        Rock r = new Rock();
        try {
            r.setSpecimenNumber(rs.getInt("specimen_no"));
            r.setCollection(rs.getString("collection"));
            r.setLocality(rs.getString("locality_found"));
            r.setCollector(rs.getString("collector_donor_name"));
            r.setPhotoPath(rs.getString("photo"));
            r.setLocation(rs.getString("current_location"));
            r.setCondition(rs.getString("condition"));
            r.setAdditionalNotes(rs.getString("notes"));
            LocalDateTime now = LocalDateTime.parse(rs.getString("last-modified"));
            r.setLastModified(now);
            r.setBloomuNo(rs.getInt("bloomu_no"));
            r.setName(rs.getString("rock_name"));
            r.setType(rs.getString("rock_Type"));
            r.setPresentStructure("structures");
            r.setAge(rs.getInt("age"));
            r.setFormation(rs.getString("formation"));
            r.setMember(rs.getString("member"));
            r.setSuite(rs.getString("suite"));
            r.setThinSection(rs.getString("thin_section"));
        } catch (SQLException e) {
            // log
            return null;
        }
        return r;
    }

    /**
     * Gets a <code> Fossil </code> object from the record
     * that is currently being pointed to in a <code> ResultSet </code>.
     *
     * @param rs given ResultSet
     * @return <code> Fossil </code> object, using information from
     *         the given <code> ResultSet </code>.
     */
    public static Fossil getFossilFromResultSet(ResultSet rs) {
        Fossil f = new Fossil();
        try {
            f.setSpecimenNumber(rs.getInt("specimen_no"));
            f.setCollection(rs.getString("collection"));
            f.setLocality(rs.getString("locality_found"));
            f.setCollector(rs.getString("collector_donor_name"));
            f.setPhotoPath(rs.getString("photo"));
            f.setLocation(rs.getString("current_location"));
            f.setCondition(rs.getString("condition"));
            f.setAdditionalNotes(rs.getString("notes"));
            LocalDateTime now = LocalDateTime.parse(rs.getString("last-modified"));
            f.setLastModified(now);
            f.setBloomuNo(rs.getInt("bloomu_no"));
            f.setSpecies(rs.getString("species"));
            f.setGenus(rs.getString("genus"));
            f.setClade1(rs.getString("higher_clade_1"));
            f.setClade2(rs.getString("higher_clade_2"));
            f.setClade3(rs.getString("higher_clade_3"));
            f.setClade4(rs.getString("higher_clade_4"));
            f.setAge(rs.getInt("age"));
            f.setFormation(rs.getString("formation"));
            f.setMember("member");
        } catch (SQLException e) {
            // log
            return null;
        }
        return f;
    }

}
