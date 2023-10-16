package be.kdg.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SailboatsTest {
    private Sailboat sailboat1;
    private Sailboat sailboat2;
    private Sailboat sailboat3;
    private Sailboat sailboat4;
    private Sailboat sailboat5;
    private Sailboats sailboats;

    @BeforeEach
    void setUp() {
        sailboat1 = new Sailboat("Teraju", "Argentina", -1, 75, Classification.Y, LocalDate.of(1995, 1, 1));
        sailboat2 = new Sailboat("Valtair", "Melbourne", 2.3, 65, Classification.CRUISER, LocalDate.of(1996, 1, 1));
        sailboat3 = new Sailboat("Alba", 1.9, 36, Classification.CRUISER, LocalDate.of(1976, 1, 1));
        sailboat4 = new Sailboat("Ricercare", "Corfu", 1.8, 49, Classification.CRUISER, LocalDate.of(1990, 1, 1));
        sailboat5 = new Sailboat("Optimist", "Antwerp", 0.5, 5, Classification.RACEBOAT, LocalDate.of(1947, 1, 1));
        sailboats = new Sailboats();
        sailboats.add(sailboat1);
        sailboats.add(sailboat2);
        sailboats.add(sailboat3);
        sailboats.add(sailboat4);
        sailboats.add(sailboat5);
    }

    @Test
    void testAddObjects() {
        Sailboat sailboat = new Sailboat("Teraju", "Argentina", -1, 75, Classification.Y, LocalDate.of(1995, 1, 1));
        sailboats.add(sailboat);
        assertNotEquals(null, sailboats.search(sailboat.getName(), sailboat.getClassification(), sailboat.getBuildYear()), () -> String.format("%s is not added to the list", sailboat));

        assertFalse(sailboats.add(sailboat), "Adding a sailboat that already exists does not return false");
    }

    @Test
    void testRemoveObject() {
        int size1 = sailboats.getSize();
        sailboats.remove(sailboat1.getName(), sailboat1.getClassification(), sailboat1.getBuildYear());
        int size2 = sailboats.getSize();
        assertEquals(1, size1 - size2, () -> String.format("Size of the list is not 1 smaller than before, size before: %d, size after: %d", size1, size2));

        assertFalse(sailboats.remove(sailboat1.getName(), sailboat1.getClassification(), sailboat1.getBuildYear()), "Removing a sailboat that does not exist does not return false");
    }

    @Test
    void testSortName(){
        List<Sailboat> sorted = sailboats.sortedBy(Sailboat::getName);
        assertAll(
                () -> assertEquals(sorted.get(0), sailboat3, () -> String.format("The firts object in the sorted list (%s) is not equal to %s", sorted.get(0), sailboat3)),
                () -> assertEquals(sorted.get(1), sailboat5, () -> String.format("The second object in the sorted list (%s) is not equal to %s", sorted.get(1), sailboat5)),
                () -> assertEquals(sorted.get(2), sailboat4, () -> String.format("The third object in the sorted list (%s) is not eqaul to %s", sorted.get(2), sailboat4)));
    }

    @Test
    void testSortDate(){
        List<Sailboat> sorted = sailboats.sortedBy(Sailboat::getBuildYear);
        Sailboat[] arr = {sailboat5, sailboat3, sailboat4, sailboat1, sailboat2};

        assertArrayEquals(arr, sorted.toArray());
    }
}