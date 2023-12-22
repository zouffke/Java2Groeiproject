package be.kdg.sailboatproject.service;

import be.kdg.sailboatproject.database.SailboatDao;
import be.kdg.sailboatproject.model.Sailboat;

import java.util.List;

public class SailboatsServiceImpl implements SailboatsService {

    //region vars

    private final SailboatDao db;

    //endregion

    //region constructors

    public SailboatsServiceImpl(SailboatDao db){
        this.db = db;
    }

    //endregion

    @Override
    public List<Sailboat> getAllSailboats() {
        return this.db.getAllSailboats();
    }

    @Override
    public boolean addSailboat(Sailboat sailboat) {
        return this.db.insert(sailboat);
    }
}
