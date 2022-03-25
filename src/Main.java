import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("/home/wwwazz/program/java/test/text.txt");

        try {
            FileInputStream stream = new FileInputStream(file);

            try {
                String line;
                while ((line = readUntil(stream, '\n')) != null) {
                    System.out.println(line);
                }
            } catch (EOFException eof) {
                System.out.println("end boucle");
            }
            stream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readUntil(FileInputStream stream, char endChar) throws IOException {
        int unsignedByte;
        String str = "";
        while ((unsignedByte = stream.read()) != endChar && unsignedByte > -1) {
            str += (char) unsignedByte;
        }
        if (unsignedByte < 0 && str.isEmpty()) {
            return null;
        }
        return str;
    }
}
