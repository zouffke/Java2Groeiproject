package be.kdg.sailboatproject.database;

import be.kdg.sailboatproject.model.Sailboat;

import java.util.List;

public interface SailboatDao {
    public List<Sailboat> getAllSailboats();
}
