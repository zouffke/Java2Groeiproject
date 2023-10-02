import be.kdg.model.Boat;
import be.kdg.model.Sailboat;
import be.kdg.model.Sailboats;
import be.kdg.reflection.ReflectionTools;

public class Demo_3 {
    public static void main(String[] args) {
        ReflectionTools.classAnalysis(Boat.class);
        System.out.println("\n------------------------------------\n");
        ReflectionTools.classAnalysis(Sailboat.class);
        System.out.println("\n------------------------------------\n");
        ReflectionTools.classAnalysis(Sailboats.class);
    }
}
