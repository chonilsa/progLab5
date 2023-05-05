package file;

import java.io.*;
import java.util.Scanner;

/**
 * The type File manager.
 */
public class FileManager {

    /**
     * Read from file string.
     *
     * @return the string
     */
    public String readFromFile() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner in = new Scanner(new FileReader(CsvParsing.getFilePath()));
            while(in.hasNext()) {
                sb.append(in.next() + '\n');
            }
            in.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * Write to file.
     *
     * @param str the str
     */
    public void writeToFile(String str) {
        FileOutputStream fos = null;
        PrintWriter pw = null;

        try {
            fos = new FileOutputStream(CsvParsing.getFilePath());
            fos.write(str.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
