import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Mohammed on 29/10/2015.
 */
public class Program {
    public static void main(String[] args){
        String inputFileName = args[0];
        int repeat = Integer.parseInt(args[1]);
        String outputFileName = args[2];

        MessageQueue<Line> q1 = new MessageQueue<Line>();
        FileIterator lines = new FileIterator(inputFileName, repeat);
        LineProducer p1 = new LineProducer(lines, q1);
        LineConsumer c1 = new LineConsumer(q1, outputFileName);

        List<Thread> threads = new ArrayList<Thread>();

        threads.add(new Thread(p1));
        threads.add(new Thread(c1));

        long start = System.currentTimeMillis();

        for(Thread t : threads){
            t.start();
        }

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long duration = System.currentTimeMillis() - start;

        System.out.println("Total duration: "+ duration + " ms.");
    }
}
