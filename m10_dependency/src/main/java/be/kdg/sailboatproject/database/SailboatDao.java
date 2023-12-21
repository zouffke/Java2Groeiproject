package be.kdg.sailboatproject.database;

import be.kdg.sailboatproject.model.Sailboat;

import java.util.List;

public interface SailboatDao {
    boolean insert(Sailboat sailboat);

    boolean delete(String naam);

    boolean update(Sailboat sailboat);

    Sailboat retrieve(String naam);

    List<Sailboat> execute(String query);

    List<Sailboat> getAllSailboats();
}
