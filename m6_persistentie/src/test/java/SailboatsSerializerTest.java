import be.kdg.data.Data;
import be.kdg.model.Sailboats;
import be.kdg.persist.SailboatsSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SailboatsSerializerTest {
    private SailboatsSerializer serializer;
    private Sailboats sailboats;

    @BeforeEach
    void setUp() {
        serializer = new SailboatsSerializer();
        sailboats = new Sailboats();
        Data.getData().forEach(sailboats::add);
    }

    @Test
    void testSerialize() {
        assertDoesNotThrow(() -> serializer.serialize(sailboats));
    }

    @Test
    void testDeserialize(){
        Sailboats sbs = assertDoesNotThrow(() -> serializer.deserialize());
        assertEquals(sailboats, sbs);
    }
}
