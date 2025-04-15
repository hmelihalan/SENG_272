import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.*;

public class FileManager{

public static synchronized List<String> readFile(String filepath) {
    List<String> lines = new ArrayList<>();
    try (RandomAccessFile file = new RandomAccessFile(filepath, "r");
         BufferedReader br = new BufferedReader(new FileReader(filepath));

    ) {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                lines.add(line);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + filepath + " - " + e.getMessage());
    }
    return lines;
}


    public static synchronized void writeFile(String filepath, List<String> lines) {
        File file = new File(filepath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            try (FileOutputStream fos = new FileOutputStream(file, false);
                 OutputStreamWriter osw = new OutputStreamWriter(fos);
                 BufferedWriter bw = new BufferedWriter(osw)) {

                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
                bw.flush();
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + filepath);
            e.printStackTrace();
        }
    }


}
