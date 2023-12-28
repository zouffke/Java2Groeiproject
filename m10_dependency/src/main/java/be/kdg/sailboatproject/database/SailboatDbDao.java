package be.kdg.sailboatproject.database;

import be.kdg.sailboatproject.data.Data;
import be.kdg.sailboatproject.exceptions.SailboatException;
import be.kdg.sailboatproject.model.Classification;
import be.kdg.sailboatproject.model.Sailboat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SailboatDbDao implements SailboatDao {

    //region vars

    private static final Logger L = Logger.getLogger(SailboatDbDao.class.getName());
    private static Connection connection;
    private static SailboatDbDao instance;

    //endregion

    //region constructors

    public static SailboatDbDao getInstance() {
        if (instance == null) {
            instance = new SailboatDbDao("jdbc:hsqldb:file:m10_dependency/database/sailboatdb");
        }
        return instance;
    }

    private SailboatDbDao(String path) {
        try {
            connection = DriverManager.getConnection(path, "sa", "");
        } catch (SQLException e) {
            L.warning(String.format("Something went wrong trying to connect to the DB.%n%s", e.getMessage()));
            throw new SailboatException(e);
        }

        try {
            createTable();
        } catch (SQLException e) {
            L.warning(String.format("There was a problem creating database tables: %s", e.getMessage()));
            throw new SailboatException(e);
        }
    }

    //endregion

    //region DB functions

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            L.severe(String.format("Something went wrong when trying to close the connection to the DB.%n%s", e.getMessage()));
        }

    }

    private void createTable() throws SQLException {
        L.info("Creating table...");
        L.info("Checking if table is already there...");
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet resultSet = dbm.getTables(null, null, "SAILBOATTABLE", null);
        if (!resultSet.next()) {
            L.info("Table 'sailboattable' does not exist yet. Creating now...");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE sailboattable " +
                    "(id INTEGER NOT NULL IDENTITY" +
                    ", name VARCHAR(30) NOT NULL" +
                    ", harbour VARCHAR(30)" +
                    ", depth FLOAT" +
                    ", length INTEGER" +
                    ", classification VARCHAR(20)" +
                    ", buildyear DATE)");

            statement.close();
            for (Sailboat sailboat : Data.getData()) {
                insert(sailboat);
            }
        } else {
            L.info("Table sailboattable already exists");
        }

        resultSet.close();
    }

    //endregion

    @Override
    public boolean insert(Sailboat sailboat) {
        String query = "INSERT INTO sailboattable VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sailboat.getName());
            preparedStatement.setString(2, sailboat.getHarbour());
            preparedStatement.setDouble(3, sailboat.getDepth());
            preparedStatement.setInt(4, sailboat.getLength());
            preparedStatement.setString(5, sailboat.getClassification().name());
            preparedStatement.setDate(6, Date.valueOf(sailboat.getBuildYear()));

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            L.warning(String.format("Something went wrong when trying to add a value to the DB.%n%s", e.getMessage()));
            throw new SailboatException(e);
        }
    }

    @Override
    public boolean delete(String naam) {
        String query = "DELETE FROM sailboattable WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             Statement statement = connection.createStatement()) {
            if (naam.equals("*")) {
                return statement.executeUpdate("DELETE FROM sailboattable") > 0;
            }
            preparedStatement.setString(1, naam);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            L.warning(String.format("Something went wrong when trying to delete a record from the DB.%n%s", e.getMessage()));
            throw new SailboatException(e);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, sailboat.getName());
            preparedStatement.setString(2, sailboat.getHarbour());
            preparedStatement.setDouble(3, sailboat.getDepth());
            preparedStatement.setInt(4, sailboat.getLength());
            preparedStatement.setString(5, sailboat.getClassification().name());
            preparedStatement.setDate(6, Date.valueOf(sailboat.getBuildYear()));
            preparedStatement.setInt(7, sailboat.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            L.warning(String.format("Something went wrong when trying to update the given object.%n%s", e.getMessage()));
            throw new SailboatException(e);
        }
    }

    @Override
    public Sailboat retrieve(String naam) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sailboattable WHERE name = '" + naam + "'")) {
            if (resultSet.next()) {
                Sailboat sailboat = new Sailboat(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("harbour"),
                        resultSet.getDouble("depth"),
                        resultSet.getInt("length"),
                        Classification.valueOf(resultSet.getString("classification")),
                        resultSet.getDate("buildyear").toLocalDate());
                return sailboat;
            } else {
                return null;
            }
        } catch (SQLException e) {
            L.warning(String.format("Something went wrong trying to fetch the object from the DB.%n%s", e.getMessage()));
            throw new SailboatException(e);
        }
    }

    //region execute

    @Override
    public List<Sailboat> execute(String query) {
        try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {
            List<Sailboat> sailboats = new ArrayList<>();

            while (resultSet.next()) {
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
            L.warning(String.format("Something went wrong when trying to give an ordered list of the DB.%n%s", e.getMessage()));
            throw new SailboatException(e);
        }
    }

    @Override
    public List<Sailboat> getAllSailboats() {
        return execute("SELECT * FROM sailboattable");
    }

    public List<Sailboat> sortedOnName() {
        return execute("SELECT * FROM sailboattable ORDER BY name");
    }

    public List<Sailboat> sortedOnHarbour() {
        return execute("SELECT * FROM sailboattable ORDER BY harbour");
    }

    public List<Sailboat> sortedOnDepth() {
        return execute("SELECT * FROM sailboattable ORDER BY depth");
    }

    public List<Sailboat> sortedOnLength() {
        return execute("SELECT * FROM sailboattable ORDER BY length");
    }

    public List<Sailboat> sortedOnClassification() {
        return execute("SELECT * FROM sailboattable ORDER BY classification");
    }

    public List<Sailboat> sortedOnBuildYear() {
        return execute("SELECT * FROM sailboattable ORDER BY buildyear");
    }

    //endregion
}
