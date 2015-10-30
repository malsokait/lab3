/**
 * Created by Mohammed on 29/10/2015.
 */
public class LineConsumer2 implements Runnable {
    MessageQueue<Line> q1;
    MessageQueue<Line> q2;

    public static final String QUERY_WORD = "TEST";
    public static final int K = 2;

    public LineConsumer2(MessageQueue<Line> q1, MessageQueue<Line> q2){
        this.q1 = q1;
        this.q2 = q2;
    }

    public void run(){
        while (!q1.isEmpty()){
            try {
                Line line = (Line) q1.take();
                if(!line.isEnd()) {
                    String[] words = Util.words(line.content);
                    for (int i = 0; i < words.length; i++) {
                        if (Util.editDistance(words[i], QUERY_WORD) <= K) {
                            q2.put(line);
                        }
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
