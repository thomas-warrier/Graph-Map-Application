package grama.graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {
    private final List<Noeud> noeuds;

    Graph() {
        noeuds = new LinkedList<>();
    }

    public static String readUntil(FileInputStream stream, String endStr) throws IOException {
        int unsignedByte = -1;
        StringBuilder str = new StringBuilder();
        int countEnd = 0;
        int size = endStr.length();
        while (countEnd < size && (unsignedByte = stream.read()) > -1) {
            if ((char) unsignedByte == endStr.charAt(countEnd)) {
                countEnd++;
            } else {
                countEnd = 0;
            }
            str.append((char) unsignedByte);
        }
        if (countEnd == size) {
            str = new StringBuilder(str.substring(0, str.length() - 2));
        } else if (unsignedByte < 0) {
            return null;
        }
        return str.toString();
    }

    public void addNoeud(Noeud noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
        }
    }

    public Noeud getOrCreate(Noeud noeud) {
        for (Noeud node : noeuds) {
            if (noeud.equals(node)) {
                return node;
            }
        }
        return noeud;
    }

    public void clear() {
        noeuds.clear();
    }

    public boolean noeudExist(Noeud noeud) {
        return noeuds.contains(noeud);
    }

    public void loadFromFile(String path) throws IOException { // y compris throw notfilefound
        File file = new File(path);


        FileInputStream stream = new FileInputStream(file);


        String line;

        Noeud noeudPrincipal = null;
        List<Lien> liens;

        Pattern namePattern = Pattern.compile("^[^:]*");

        while ((line = readUntil(stream, ";;")) != null) {
            line = line.replaceAll("[\n\t\r]", ""); // remove posible '\n' or '\t' or '\r'

            Matcher nameMatch = namePattern.matcher(line);
            if (nameMatch.find()) {
                String name = nameMatch.group().replaceAll(" ", "");
                char typeNode = name.charAt(0);
                String nameNode = name.substring(2);
                noeudPrincipal = new Noeud(typeNode, nameNode);

                line = line.substring(nameMatch.group().length() + 1); // remove the name from the reste of the String
            }
            String[] coupleLienNeoud = line.split(";");
            liens = new ArrayList<>();
            for (String couple : coupleLienNeoud) {
                String[] both = couple.split("::");

                both[0] = both[0].replaceAll(" ", "");
                char type = both[0].charAt(0);
                int distance = Integer.parseInt(both[0].substring(2));

                both[1] = both[1].replaceAll(" ", "");
                char typeDst = both[1].charAt(0);
                String nameDst = both[1].substring(2);

                Noeud node = new Noeud(typeDst, nameDst);
                Lien lien = new Lien(type, distance, node);

                liens.add(lien);
            }
            System.out.println(noeudPrincipal + " = " + liens);

            //on a le noeudPrincipal et une list de lien (liens) qui parte de celui ci (avec les destination)

        }

        stream.close();

    }
}
