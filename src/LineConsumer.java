import java.util.concurrent.BlockingQueue;

/**
 * Created by Mohammed on 29/10/2015.
 */
public class LineConsumer implements Runnable {
    MessageQueue<Line> q1;
    String outputFileName;


    public static final int K = 1;

    public LineConsumer(MessageQueue<Line> q1, String outputFileName){
        this.q1 = q1;
        this.outputFileName = outputFileName;
    }

    public void run(){
        while (!q1.isEmpty()){
            try {
                Line line = (Line) q1.take();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
