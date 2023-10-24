package be.kdg.data;

import be.kdg.model.Classification;
import be.kdg.model.Sailboat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Sailboat> getData() {
        List<Sailboat> sailboats = new ArrayList<>();

        sailboats.add(new Sailboat("Teraju", "Argentina", -1, 75, Classification.Y, LocalDate.of(1995, 1, 1)));
        sailboats.add(new Sailboat("Valtair", "Melbourne", 2.3, 65, Classification.CRUISER, LocalDate.of(1996, 1, 1)));
        sailboats.add(new Sailboat("Sanada", 3, 57, Classification.CRUISER, LocalDate.of(2008, 1, 1)));
        sailboats.add(new Sailboat("Obsession", 3.2, 50, Classification.CRUISER, LocalDate.of(2004, 1, 1)));
        sailboats.add(new Sailboat("goodspeed", 2, 40, Classification.CRUISE_RACER, LocalDate.of(2004, 1, 1)));
        sailboats.add(new Sailboat("Enya", 1.7, 33, Classification.CRUISER, LocalDate.of(1980, 1, 1)));
        sailboats.add(new Sailboat("La pologne 2", "Ilawa", 1.75, 27, Classification.SLOOP, LocalDate.of(2020, 1, 1)));
        sailboats.add(new Sailboat("Alba", 1.9, 36, Classification.CRUISER, LocalDate.of(1976, 1, 1)));
        sailboats.add(new Sailboat("BlackPearl", "Houston", 5, 80, Classification.CRUISER, LocalDate.of(2023, 1, 1)));
        sailboats.add(new Sailboat("The big easy", "Stavoren", 1.55, 30, Classification.CRUISER, LocalDate.of(2000, 1, 1)));
        sailboats.add(new Sailboat("Hangard", "Brussel", 1.5, 34, Classification.CRUISER, LocalDate.of(1970, 1, 1)));
        sailboats.add(new Sailboat("Ricercare", "Corfu", 1.8, 49, Classification.CRUISER, LocalDate.of(1990, 1, 1)));
        sailboats.add(new Sailboat("River blues", 1.6, 37, Classification.CRUISE_RACER, LocalDate.of(2006, 1, 1)));
        sailboats.add(new Sailboat("Santana", 2, 75, Classification.CRUISER, LocalDate.of(1988, 1, 1)));
        sailboats.add(new Sailboat("Optimist", "Antwerp", 0.5, 5, Classification.RACEBOAT, LocalDate.of(1947, 1, 1)));

        return sailboats;
    }
}
