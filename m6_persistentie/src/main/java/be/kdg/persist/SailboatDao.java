package be.kdg.persist;

import be.kdg.model.Sailboat;

import java.util.List;

public interface SailboatDao {
    boolean insert(Sailboat sailboat);
    boolean delete(String naam);
    boolean update(Sailboat sailboat);
    Sailboat retrieve(String naam);
    List<Sailboat> sortedOn(String query);
}
