import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mohammed on 29/10/2015.
 */
public class FileIterator implements Iterable<Line> {
    private List<Line> list = new ArrayList<>();

    public FileIterator(String filename, int repeat){
        int i = 0;
        String s;
        long lineNumber = 0;

        Path file = Paths.get(filename);
        while(i < repeat){
            try(BufferedReader reader = Files.newBufferedReader(file)){
                while((s = reader.readLine()) != null){
                    lineNumber++;
                    list.add(new Line(s, lineNumber));
                }
            } catch (IOException e){
                System.err.println(e);
                System.exit(1);
            }
            i++;
        }

    }

    @Override
    public Iterator<Line> iterator() {
        return list.iterator();
    }

}