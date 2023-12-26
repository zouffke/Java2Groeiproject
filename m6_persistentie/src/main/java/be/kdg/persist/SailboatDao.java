package be.kdg.persist;

import be.kdg.model.Sailboat;
import java.util.List;

/**
 * This interface defines the methods for interacting with the database for Sailboat objects.
 * It includes methods for inserting, deleting, updating, retrieving, and sorting Sailboat objects.
 */
public interface SailboatDao {
    /**
     * Inserts a Sailboat object into the database.
     *
     * @param sailboat the Sailboat object to be inserted
     * @return true if the Sailboat object was inserted successfully, false otherwise
     */
    boolean insert(Sailboat sailboat);

    /**
     * Deletes a Sailboat object from the database.
     *
     * @param naam the name of the Sailboat object to be deleted
     * @return true if the Sailboat object was deleted successfully, false otherwise
     */
    boolean delete(String naam);

    /**
     * Updates a Sailboat object in the database.
     *
     * @param sailboat the Sailboat object to be updated
     * @return true if the Sailboat object was updated successfully, false otherwise
     */
    boolean update(Sailboat sailboat);

    /**
     * Retrieves a Sailboat object from the database.
     *
     * @param naam the name of the Sailboat object to be retrieved
     * @return the Sailboat object if found, null otherwise
     */
    Sailboat retrieve(String naam);

    /**
     * Returns a list of Sailboat objects sorted based on a given query.
     *
     * @param query the query to be used for sorting
     * @return a list of sorted Sailboat objects
     */
    List<Sailboat> sortedOn(String query);
}