/**
 * Created by Mohammed on 29/10/2015.
 */
public class Line {
    String content;
    long lineNumber;

    public Line(String content, long lineNumber){
        this.content = content;
        this.lineNumber = lineNumber;
    }

    public boolean isEnd() {
        return lineNumber < 0;
    }
}

