import java.util.ArrayList;
import java.util.List;

/**
 * Created by 100436482 on 30/10/2015.
 */
public class Program2 {
    public static void main (String[] args){
        String inputFileName = args[0];
        int repeat = Integer.parseInt(args[1]);
        String outputFileName = args[2];
        int numWorkers = Integer.parseInt(args[3]);

        MessageQueue<Line> q1 = new MessageQueue<Line>();
        MessageQueue<Line> q2 = new MessageQueue<Line>();
        FileIterator lines = new FileIterator(inputFileName, repeat);
        List<Thread> threads = new ArrayList<Thread>();

        threads.add(new Thread(new LineProducer(lines, q1)));

        for(int i = 0; i < numWorkers; i++){
            threads.add(new Thread (new LineConsumer2(q1, q2)));
        }

        threads.add(new Thread(new ResultConsumer(q2,outputFileName)));

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

        System.out.println("Total duration: " + duration + " ms.");
    }
}
