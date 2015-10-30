import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Created by Mohammed on 29/10/2015.
 */
public class LineConsumer implements Runnable {
    MessageQueue<Line> q1;
    String outputFileName;
    ArrayList<Line> lines = new ArrayList<>();

    public static final String QUERY_WORD = "TEST";
    public static final int K = 2;

    public LineConsumer(MessageQueue<Line> q1, String outputFileName){
        this.q1 = q1;
        this.outputFileName = outputFileName;
    }

    public void run(){
        while (!q1.isEmpty()){
            try {
                Line line = (Line) q1.take();
                if(!line.isEnd()) {
                    String[] words = Util.words(line.content);
                    for(int i = 0; i < words.length; i++){
                        if(Util.editDistance(words[i], QUERY_WORD) <= K){
                            lines.add(line);
                            System.out.println("Line number: " + line.lineNumber + ", matched word: " + words[i] + ", Edit distance: " + Util.editDistance(words[i], QUERY_WORD));
                            i = words.length;
                        }
                    }

                }
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName));
                for(Line outputLine : lines){
                    writer.write(outputLine.content);
                    writer.newLine();
                }
                writer.close();



            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
