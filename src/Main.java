import grama.graph.Lien;
import grama.graph.Noeud;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        File file = new File("/home/wwwazz/program/java/test/text.txt");

        try {
            FileInputStream stream = new FileInputStream(file);


            String line;
            int i = 0;
            Pattern namePattern = Pattern.compile("^[^:]*");

            while ((line = readUntil(stream, ";;")) != null) {
                line = line.replaceAll("[\n\t\r]", ""); // remove posible '\n' or '\t' or '\r'

                String name = null;
                Matcher nameMatch = namePattern.matcher(line);
                if (nameMatch.find()) {
                    name = nameMatch.group();
                    line = line.substring(nameMatch.group().length() + 1); // remove the name from the reste of the String
                }
                String[] coupleLienNeoud = line.split(";");
                List<Lien> liens = new ArrayList<>();
                for (String couple : coupleLienNeoud){
                    String[] both = couple.split("::");

                    both[0] = both[0].replaceAll(" ", "");
                    char type = both[0].charAt(0);
                    int distance = Integer.valueOf(both[0].substring(2));

                    both[1] = both[1].replaceAll(" ", "");
                    char typeDst = both[1].charAt(0);
                    String nameDst = both[1].substring(2);

                    Noeud node = new Noeud(typeDst, nameDst);
                    Lien lien = new Lien(type, distance, node);

                    System.out.println(lien);
                    liens.add(lien);
                }



//                System.out.println(both[0] + "=" + both[1].split(";"));
            }

            stream.close();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
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

    public static String readUntil(FileInputStream stream, String endStr) throws IOException {
        int unsignedByte = -1;
        String str = "";
        int countEnd = 0;
        int size = endStr.length();
        while (countEnd < size && (unsignedByte = stream.read()) > -1) {
            if ((char) unsignedByte == endStr.charAt(countEnd)) {
                countEnd++;
            } else {
                countEnd = 0;
            }
            str += (char) unsignedByte;
        }
        if (countEnd == size) {
            str = str.substring(0, str.length() - 2);
        } else if (unsignedByte < 0) {
            return null;
        }
        return str;
    }
}
