package grama.graph;

import grama.exceptions.FormatFileException;
import grama.formater.StringFormater;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {

    private final List<Noeud> listNoeud;

    public Graph() {
        listNoeud = new LinkedList<>();
    }

    public List<Noeud> getListNoeud() {
        return listNoeud;
    }

    public void addNoeud(Noeud noeud) {
        if (!noeudExist(noeud)) {
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
        //si pas trouvé c'est qu'il doit être créé
        //donc ajouté à la liste de neud
        addNoeud(noeud);
        return noeud;
    }

    public void clear() {
        listNoeud.clear();
    }

    public boolean noeudExist(Noeud noeud) {
        return listNoeud.contains(noeud);
    }

    public void loadFromString(String str) throws FormatFileException { // compren throw notfilefound
        String fileContent = str.replaceAll("[\n\t\r]", "").trim(); // remove '\n' or '\t' or '\r' et useless space

        String[] eachNode = fileContent.split(";;");

        Noeud noeudPrincipal;

        Pattern namePattern = Pattern.compile("^[^:]*");
        for (String line : eachNode) {
            Matcher mainNodeMatch = namePattern.matcher(line);
            if (mainNodeMatch.find()) {
                String mainNode = mainNodeMatch.group();
                String[] formatNode = StringFormater.getCoupleFormatCharStr(mainNode);

                char typeNode = formatNode[0].charAt(0);
                String nameNode = formatNode[1];

                noeudPrincipal = new Noeud(typeNode, nameNode);

                noeudPrincipal = getOrCreate(noeudPrincipal);

                line = line.substring(mainNodeMatch.group().length() + 1); // remove the name from the reste of the String
            } else {
                throw new FormatFileException(line);
            }
            String[] coupleLienNeoud = line.split(";");
            for (String couple : coupleLienNeoud) {
                String[] both = couple.split("::");
                if (both.length != 2) {
                    throw new FormatFileException();
                }
                String lienStr = both[0];
                String neoudStr = both[1];

                String[] splitLienStr = StringFormater.getCoupleFormatCharStr(lienStr);
                char type = splitLienStr[0].charAt(0);
                int distance = Integer.parseInt(splitLienStr[1]);

                String[] splitNeoudStr = StringFormater.getCoupleFormatCharStr(neoudStr);
                char typeDst = splitNeoudStr[0].charAt(0);
                String nameDst = splitNeoudStr[1];

                Noeud node = new Noeud(typeDst, nameDst);
                Lien lien = new Lien(type, distance, noeudPrincipal, getOrCreate(node));

                lien = getOrCreate(lien);

                noeudPrincipal.addLien(lien);
            }
            addNoeud(noeudPrincipal);
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (Noeud node : getListNoeud()) {
            str += node + " = " + node.getListLien() + "\n";
        }
        return str;
    }

}
