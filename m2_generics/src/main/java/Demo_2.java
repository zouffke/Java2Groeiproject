import be.kdg.data.Data;
import be.kdg.generics.PriorityQueue;
import be.kdg.model.Sailboat;

import java.util.Random;

public class Demo_2 {
    public static void main(String[] args) {
        PriorityQueue<String> myQueue = new PriorityQueue<>();
        myQueue.enqueue("Tokio", 2);
        myQueue.enqueue("Denver", 5);
        myQueue.enqueue("Rio", 2);
        myQueue.enqueue("Oslo", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van Tokio: " + myQueue.search("Tokio"));
        System.out.println("positie van Nairobi: " + myQueue.search("Nairobi"));
        for(int i = 0; i < 4; i++) {
            System.out.println("Dequeue: " + myQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + myQueue.getSize());


        var random = new Random();
        var sailboatsQue = new PriorityQueue<>();

        for (Sailboat sailboat : Data.getData()) {
            sailboatsQue.enqueue(sailboat, random.nextInt(5) + 1);
        }

        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(sailboatsQue.toString());
        System.out.println("aantal: " + sailboatsQue.getSize());
        for(int i = 0; i < 4; i++) {
            System.out.println("Dequeue: " + sailboatsQue.dequeue());
        }
        System.out.println("Size na dequeue: " + sailboatsQue.getSize());
    }
}
