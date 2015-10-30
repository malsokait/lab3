import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by 100436482 on 30/10/2015.
 */
public class ResultConsumer implements Runnable {
    String outputFileName;
    MessageQueue<Line> q2;
    public ResultConsumer(MessageQueue<Line> q2, String outputFileName){
        this.outputFileName = outputFileName;
        this.q2 = q2;
    }

    public void run(){
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName));
            while(!q2.isEmpty()){
                Line line = (Line) q2.take();
                writer.write(line.content);
                writer.newLine();
            }
            writer.close();
        } catch(InterruptedException | IOException e){
            e.printStackTrace();
        }
    }
}
