import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Mohammed on 29/10/2015.
 */
public class MessageQueue<Line> extends LinkedBlockingQueue{
    public static final String FILTERED_PHRASE = "distributed systems";

    public void putWithFilter(Object o, String s) throws InterruptedException {
        if(!s.toLowerCase().contains(FILTERED_PHRASE))
                this.put(o);

    }
}
