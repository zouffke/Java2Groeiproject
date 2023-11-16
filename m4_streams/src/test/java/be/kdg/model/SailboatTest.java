package be.kdg.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SailboatTest {

    private Sailboat sailboat1;
    private Sailboat sailboat2;

    @BeforeEach
    void setUp() {
        sailboat1 = new Sailboat("Teraju", "Argentina", 2, 75, Classification.Y, LocalDate.of(1995, 1, 1));
        sailboat2 = new Sailboat("Valtair", "Melbourne", 2.3, 65, Classification.CRUISER, LocalDate.of(1996, 1, 1));
    }

    @Test
    void testEquals() {
        assertNotEquals(sailboat1, sailboat2, () -> String.format("%s is equal to %s", sailboat1, sailboat2));
        Sailboat sailboat3 = new Sailboat("Teraju", "Antwerp", 3, 40, Classification.Y, LocalDate.of(1995, 1, 1));
        assertEquals(sailboat1, sailboat3, () -> String.format("%s is not equal to %n%s" +
                "%nAttributes: name, Classification and buildyear have to be eqaul to eachother at the same time" +
                "%nGiven attributes that need to be the same: %s, %s, %s", sailboat1, sailboat3, sailboat1.getName(), sailboat1.getClassification(), sailboat1.getBuildYear()));
    }

    @Test
    void testIllegalName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Sailboat("", "Antwerp", 3, 40, Classification.Y, LocalDate.of(1995, 1, 1)), "having a name smaller than 1 character does not throw an error");
    }

    @Test
    void testLegalDate() {
        assertDoesNotThrow(() -> new Sailboat("Siva", "Antwerp", 3, 40, Classification.Y, LocalDate.now()), () -> String.format("%s returns an error", LocalDate.now()));
    }

    @Test
    void testCompareTo(){
        Sailboat sailboat = new Sailboat("Teraju", "Argentina", 2, 75, Classification.Y, LocalDate.of(1995, 1, 1));
        assertEquals(sailboat1.compareTo(sailboat), 0, () -> String.format("Compare function does not return 0 when using %s and %s", sailboat1, sailboat));
    }

    @Test
    void testDepth(){
        assertEquals(sailboat1.getDepth(), sailboat2.getDepth(), 1.0, () -> String.format("Depth of boat 1: %.1f is not equal to depth of boat 2: %.1f with a delta of: %.1f", sailboat1.getDepth(), sailboat2.getDepth(), 1.0));
    }
}