

/**
 * Created by Mohammed on 29/10/2015.
 */
public class LineProducer implements Runnable {
    FileIterator input;
    MessageQueue<Line> q1;
    public LineProducer(FileIterator input, MessageQueue<Line> q1){
        this.input = input;
        this.q1 = q1;
    }

    public void run(){
        for(Line line : input){
            try {
                q1.putWithFilter(line, line.content);
                q1.put(line);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        q1.add(new Line("END-OF-FILE", -1));
    }
}
