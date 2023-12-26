package be.kdg.persist;

import be.kdg.model.Classification;
import be.kdg.model.Sailboat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class implements the SailboatDao interface and provides methods for interacting with the database for Sailboat objects.
 * It includes methods for inserting, deleting, updating, retrieving, and sorting Sailboat objects.
 */
public class SailboatDbDao implements SailboatDao {

    private Connection connection = null;
    private static final Logger LOGGER = Logger.getLogger("be.kdg.persist.SailboatDbDao");

    /**
     * Constructor that establishes a connection to the database and creates a statement.
     *
     * @param path the path to the database
     */
    public SailboatDbDao(String path) {
        try {
            connection = DriverManager.getConnection(path, "sa", "");
            createTable();
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong trying to connect to the DB.%n%s", e.getMessage()));
        }
    }

    /**
     * Closes the connection to the database.
     */
    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to close the connection to the DB.%n%s", e.getMessage()));
        }

    }

    /**
     * Creates a table in the database.
     */
    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS sailboattable");
            String query = "CREATE TABLE sailboattable " +
                    "(id INTEGER NOT NULL IDENTITY" +
                    ", name VARCHAR(30) NOT NULL" +
                    ", harbour VARCHAR(30)" +
                    ", depth FLOAT" +
                    ", length INTEGER" +
                    ", classification VARCHAR(20)" +
                    ", buildyear DATE)";
            statement.execute(query);
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to create the table in the DB.%n%s", e.getMessage()));
        }
    }

    /**
     * Inserts a Sailboat object into the database.
     *
     * @param sailboat the Sailboat object to be inserted
     * @return true if the Sailboat object was inserted successfully, false otherwise
     */
    @Override
    public boolean insert(Sailboat sailboat) {
        String query = "INSERT INTO sailboattable VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prep = connection.prepareStatement(query)) {
            prep.setString(1, sailboat.getName());
            prep.setString(2, sailboat.getHarbour());
            prep.setDouble(3, sailboat.getDepth());
            prep.setInt(4, sailboat.getLength());
            prep.setString(5, sailboat.getClassification().name());
            prep.setDate(6, Date.valueOf(sailboat.getBuildYear()));

            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to add a value to the DB.%n%s", e.getMessage()));
            return false;
        }
    }

    /**
     * Deletes a Sailboat object from the database.
     *
     * @param naam the name of the Sailboat object to be deleted
     * @return true if the Sailboat object was deleted successfully, false otherwise
     */
    @Override
    public boolean delete(String naam) {
        String query = "DELETE FROM sailboattable WHERE name = ?";

        try (Statement statement = connection.createStatement();
             PreparedStatement prep = connection.prepareStatement(query)) {
            if (naam.equals("*")) {
                return statement.executeUpdate("DELETE FROM sailboattable") > 0;
            }
            prep.setString(1, naam);

            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to delete a record from the DB.%n%s", e.getMessage()));
            return false;
        }
    }

    /**
     * Updates a Sailboat object in the database.
     *
     * @param sailboat the Sailboat object to be updated
     * @return true if the Sailboat object was updated successfully, false otherwise
     */
    @Override
    public boolean update(Sailboat sailboat) {
        String query = "UPDATE sailboattable SET " +
                "name=?," +
                "harbour=?," +
                "depth=?," +
                "length=?," +
                "classification=?," +
                "buildyear=?" +
                " WHERE id=?";
        try (PreparedStatement prep = connection.prepareStatement(query)) {
            prep.setString(1, sailboat.getName());
            prep.setString(2, sailboat.getHarbour());
            prep.setDouble(3, sailboat.getDepth());
            prep.setInt(4, sailboat.getLength());
            prep.setString(5, sailboat.getClassification().name());
            prep.setDate(6, Date.valueOf(sailboat.getBuildYear()));
            prep.setInt(7, sailboat.getId());

            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to update the given object.%n%s", e.getMessage()));
            return false;
        }
    }

    /**
     * Retrieves a Sailboat object from the database.
     *
     * @param naam the name of the Sailboat object to be retrieved
     * @return the Sailboat object if found, null otherwise
     */
    @Override
    public Sailboat retrieve(String naam) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sailboattable WHERE name = '" + naam + "'")) {
            if (resultSet.next()) {
                return new Sailboat(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("harbour"),
                        resultSet.getDouble("depth"),
                        resultSet.getInt("length"),
                        Classification.valueOf(resultSet.getString("classification")),
                        resultSet.getDate("buildyear").toLocalDate());
            } else return null;
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong trying to fetch the object from the DB.%n%s", e.getMessage()));
            return null;
        }
    }

    /**
     * Returns a list of Sailboat objects sorted based on a given query.
     *
     * @param query the query to be used for sorting
     * @return a list of sorted Sailboat objects
     */
    @Override
    public List<Sailboat> sortedOn(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            List<Sailboat> sailboats = new ArrayList<>();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                sailboats.add(new Sailboat(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("harbour"),
                        resultSet.getDouble("depth"),
                        resultSet.getInt("length"),
                        Classification.valueOf(resultSet.getString("classification")),
                        resultSet.getDate("buildyear").toLocalDate()));
            }
            return sailboats;
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to give an ordered list of the DB.%n%s", e.getMessage()));
            return null;
        }
    }

    //region sorted methods

    /**
     * Returns a list of Sailboat objects sorted based on their names.
     *
     * @return a list of Sailboat objects sorted by name
     */
    public List<Sailboat> sortedOnName() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY name");
    }

    /**
     * Returns a list of Sailboat objects sorted based on their harbours.
     *
     * @return a list of Sailboat objects sorted by harbour
     */
    public List<Sailboat> sortedOnHarbour() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY harbour");
    }

    /**
     * Returns a list of Sailboat objects sorted based on their depths.
     *
     * @return a list of Sailboat objects sorted by depth
     */
    public List<Sailboat> sortedOnDepth() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY depth");
    }

    /**
     * Returns a list of Sailboat objects sorted based on their lengths.
     *
     * @return a list of Sailboat objects sorted by length
     */
    public List<Sailboat> sortedOnLength() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY length");
    }

    /**
     * Returns a list of Sailboat objects sorted based on their classifications.
     *
     * @return a list of Sailboat objects sorted by classification
     */
    public List<Sailboat> sortedOnClassification() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY classification");
    }

    /**
     * Returns a list of Sailboat objects sorted based on their build years.
     *
     * @return a list of Sailboat objects sorted by build year
     */
    public List<Sailboat> sortedOnBuildYear() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY buildyear");
    }

    //endregion
}