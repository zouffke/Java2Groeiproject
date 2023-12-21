package be.kdg.sailboatproject.service;

import be.kdg.sailboatproject.model.Sailboat;

import java.util.List;

public interface SailboatsService {
    public List<Sailboat> getAllSailboats();
    public boolean addSailboat(Sailboat sailboat);
}
