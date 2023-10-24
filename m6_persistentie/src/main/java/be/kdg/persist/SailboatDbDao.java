package be.kdg.persist;

import be.kdg.model.Classification;
import be.kdg.model.Sailboat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SailboatDbDao implements SailboatDao {

    private Connection connection = null;
    private static final Logger LOGGER = Logger.getLogger("be.kdg.persist.SailboatDbDao");
    private Statement statement = null;
    private ResultSet resultSet = null;

    public SailboatDbDao(String path) {
        try {
            connection = DriverManager.getConnection(path, "sa", "");
            statement = connection.createStatement();
            createTable();
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong trying to connect to the DB.%n%s", e.getMessage()));
        }
    }

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to close the connection to the DB.%n%s", e.getMessage()));
        }

    }

    private void createTable() {
        try {
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

    @Override
    public boolean insert(Sailboat sailboat) {
        String query = "INSERT INTO sailboattable VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prep = connection.prepareStatement(query);

            prep.setString(1, sailboat.getName());
            prep.setString(2, sailboat.getHarbour());
            prep.setDouble(3, sailboat.getDepth());
            prep.setInt(4, sailboat.getLength());
            prep.setString(5, sailboat.getClassification().name());
            prep.setDate(6, Date.valueOf(sailboat.getBuildYear()));

            boolean succes = prep.executeUpdate() > 0;
            prep.close();
            return succes;
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to add a value to the DB.%n%s", e.getMessage()));
            return false;
        }
    }

    @Override
    public boolean delete(String naam) {
        String query = "DELETE FROM sailboattable WHERE name = ?";

        try {
            PreparedStatement prep = connection.prepareStatement(query);

            prep.setString(1, naam);


            boolean succes = prep.executeUpdate() > 0;
            prep.close();
            return succes;
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to delete a record from the DB.%n%s", e.getMessage()));
            return false;
        }
    }

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
        try {
            PreparedStatement prep = connection.prepareStatement(query);

            prep.setString(1, sailboat.getName());
            prep.setString(2, sailboat.getHarbour());
            prep.setDouble(3, sailboat.getDepth());
            prep.setInt(4, sailboat.getLength());
            prep.setString(5, sailboat.getClassification().name());
            prep.setDate(6, Date.valueOf(sailboat.getBuildYear()));
            prep.setInt(7, sailboat.getId());

            boolean succes = prep.executeUpdate() > 0;
            prep.close();
            return succes;
        } catch (SQLException e) {
            LOGGER.severe(String.format("Something went wrong when trying to update the given object.%n%s", e.getMessage()));
            return false;
        }
    }

    @Override
    public Sailboat retrieve(String naam) {
        try {
            resultSet = statement.executeQuery("SELECT * FROM sailboattable WHERE name = '" + naam +"'");
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

    @Override
    public List<Sailboat> sortedOn(String query) {
        try {
            List<Sailboat> sailboats = new ArrayList<>();
            resultSet = statement.executeQuery(query);
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

    public List<Sailboat> sortedOnName() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY name");
    }

    public List<Sailboat> sortedOnHarbour() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY harbour");
    }

    public List<Sailboat> sortedOnDepth() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY depth");
    }

    public List<Sailboat> sortedOnLength() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY length");
    }

    public List<Sailboat> sortedOnClassification() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY classification");
    }

    public List<Sailboat> sortedOnBuildYear() {
        return sortedOn("SELECT * FROM sailboattable ORDER BY buildyear");
    }
}
