import be.kdg.data.Data;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import be.kdg.persist.SailboatDbDao;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SailboatDbDaoTest {
    private static SailboatDbDao db;

    @BeforeAll
    static void beforeAll() {
        db = new SailboatDbDao("jdbc:hsqldb:file:db/sailboats");
    }

    @AfterAll
    static void afterAll() {
        db.close();
    }

    @BeforeEach
    void setUp() {
        Data.getData().forEach(db::insert);
    }

    @AfterEach
    void tearDown() {
        db.delete("*");
    }

    @Test
    void testInsert() {
        assertEquals(Data.getData().size(), db.sortedOnName().size());
    }

    @Test
    void testRetrieveUpdate() {
        Sailboat sailboat1 = db.retrieve("Teraju");
        sailboat1.setName("Test");
        assertTrue(db.update(sailboat1));
        assertEquals(sailboat1.getId(), db.retrieve("Test").getId());
    }

    @Test
    void testDelete() {
        int length = db.sortedOnLength().size();
        assertTrue(db.delete("Teraju"));
        assertEquals(length - 1, db.sortedOnLength().size());
        assertFalse(db.delete("Teraju"));
    }

    @Test
    void testSort() {
        Sailboats sailboats = new Sailboats();
        Data.getData().forEach(sailboats::add);

        assertEquals(sailboats.sortedOnBuildYear(), db.sortedOnBuildYear());
    }
}
