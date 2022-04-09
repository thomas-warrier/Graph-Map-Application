package grama.graph;

import grama.exceptions.FormatFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {
    private final List<Noeud> listNoeud;

    public Graph() {
        listNoeud = new LinkedList<>();
    }

    public static String readFile(File file) throws IOException {
        if (file == null) {
            return "";
        }
        int unsignedByte;

        FileInputStream stream = new FileInputStream(file);
        StringBuilder str = new StringBuilder();// more efficient than  StringBuffer
        while ((unsignedByte = stream.read()) > -1) {
            str.append((char) unsignedByte);
        }

        stream.close();
        return str.toString();
    }

    private static String[] getCoupleFormatCharStr(String couple) throws FormatFileException {
        String[] splited = couple.trim().split(",");
        for (int i = 0; i < splited.length; i++) {
            splited[i] = splited[i].trim();
        }
        if (splited.length != 2 || splited[0].length() != 1) {
            throw new FormatFileException(couple);
        }

        return splited;
    }

    public List<Noeud> getListNoeud() {
        return listNoeud;
    }

    public void addNoeud(Noeud noeud) {
        if (!listNoeud.contains(noeud)) {
            listNoeud.add(noeud);
        }
    }

    public Lien getOrCreate(Lien lien) {

        for (Noeud node : listNoeud) {
            for (Lien link : node.getListLien()) {
                if (lien.equals(link)) {
                    return link;
                }
            }
        }
        return lien;
    }

    public Noeud getOrCreate(Noeud noeud) {
        for (Noeud node : listNoeud) {
            if (noeud.equals(node)) {
                return node;
            }
        }
        return noeud;
    }

    public void clear() {
        listNoeud.clear();
    }

    public boolean noeudExist(Noeud noeud) {
        return listNoeud.contains(noeud);
    }

    public void loadFromFile(String path) throws IOException, FormatFileException { // compren throw notfilefound
        File file = new File(path);

        String fileContent = readFile(file);

        fileContent = fileContent.replaceAll("[\n\t\r]", "").trim(); // remove '\n' or '\t' or '\r' et useless space

        String[] eachNode = fileContent.split(";;");


        Noeud noeudPrincipal;
        List<Lien> liens;

        Pattern namePattern = Pattern.compile("^[^:]*");
        for (String line : eachNode) {
            Matcher mainNodeMatch = namePattern.matcher(line);
            if (mainNodeMatch.find()) {
                String mainNode = mainNodeMatch.group();
                String[] formatNode = getCoupleFormatCharStr(mainNode);

                char typeNode = formatNode[0].charAt(0);
                String nameNode = formatNode[1];

                noeudPrincipal = new Noeud(typeNode, nameNode);

                noeudPrincipal = getOrCreate(noeudPrincipal);

                line = line.substring(mainNodeMatch.group().length() + 1); // remove the name from the reste of the String
            } else {
                throw new FormatFileException(line);
            }
            String[] coupleLienNeoud = line.split(";");
            liens = noeudPrincipal.getListLien();
            for (String couple : coupleLienNeoud) {
                String[] both = couple.split("::");
                if (both.length != 2) {
                    throw new FormatFileException();
                }
                String lienStr = both[0];
                String neoudStr = both[1];

                String[] splitLienStr = getCoupleFormatCharStr(lienStr);
                char type = splitLienStr[0].charAt(0);
                int distance = Integer.parseInt(splitLienStr[1]);

                String[] splitNeoudStr = getCoupleFormatCharStr(neoudStr);
                char typeDst = splitNeoudStr[0].charAt(0);
                String nameDst = splitNeoudStr[1];

                Noeud node = new Noeud(typeDst, nameDst);
                Lien lien = new Lien(type, distance, noeudPrincipal, getOrCreate(node));

                lien = getOrCreate(lien);

                liens.add(lien);
            }

            listNoeud.add(noeudPrincipal);
        }


    }
}
