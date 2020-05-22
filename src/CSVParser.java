//https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// parsed through CSV file
public class CSVParser {
    public static VideoListOrdered parse(String input, int category) {

        String csvFile = input;
        String line = "";
        VideoListOrdered list = new VideoListOrdered(5);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] splits = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (Integer.parseInt(splits[4])== category){
                    VideoNode current = new VideoNode(splits);
                    list.add(current);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

