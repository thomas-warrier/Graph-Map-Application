package grama.graph;

import grama.exceptions.FormatFileException;
import grama.exceptions.MauvaisTypeException;
import grama.formater.StringFormater;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * classe representant le graphe lui même
 * @author virgile
 */
public class Graph {

    private final List<Noeud> listNoeud;//pour simplifier on stocke la liste de tout les noeuds ici (pourra être trouvé en double dans listNoeudOfType
    private final HashMap<Noeud.Type, List<Noeud>> listNoeudOfType;
    private final HashMap<Lien.Type, List<Lien>> listLienOfType;

    public Graph() {
        listNoeud = new LinkedList<>();
        listNoeudOfType = new HashMap<>();
        listLienOfType = new HashMap<>();
    }

    public List<Noeud> getListNoeud() {
        return listNoeud;
    }

    /**
     * possibilité de récuperer certain Noeud en fonction de leur type, la liste n'est créée qu'une fois
     *
     * @param t le type des noeuds à récuperer
     * @return la liste de noeuds de type t
     */
    public List<Noeud> getListNoeudOfType(Noeud.Type t) {
        if (listNoeudOfType.containsKey(t)) {
            return listNoeudOfType.get(t);
        } else {
            List<Noeud> listNoeudOfSpecifiedType = new ArrayList<>();
            for (Noeud noeud : getListNoeud()) {
                if (noeud.getTypeLieu().estDeType(t)) {
                    listNoeudOfSpecifiedType.add(noeud);
                }
            }
            listNoeudOfType.put(t, listNoeudOfSpecifiedType);
            return getListNoeudOfType(t);
        }
    }

    /**
     * crée au besoin et retourne tous les liens de type t, la liste n'est créé qu'une fois
     *
     * @param t le type des liens à récuperer
     * @return la liste de liens
     */
    public List<Lien> getListLienOfType(Lien.Type t) {
        if (listLienOfType.containsKey(t)) {
            return listLienOfType.get(t);
        } else {
            List<Lien> listLienOfSpecifiedType = new ArrayList<>();

            for (Noeud noeud : getListNoeud()) {
                for (Lien lien : noeud.getListLien()) {
                    if (lien.getTypeLien().estDeType(t) && !listLienOfSpecifiedType.contains(lien)) {
                        listLienOfSpecifiedType.add(lien);
                    }
                }
            }

            listLienOfType.put(t, listLienOfSpecifiedType);
            return getListLienOfType(t);
        }

    }

    /**
     * ajoute un noeud s'il n'est pas déjà présent
     *
     * @param noeud le noeud à ajouter
     */
    public void addNoeud(Noeud noeud) {
        if (!noeudExist(noeud)) {
            listNoeud.add(noeud);
        }
    }

    /**
     * récupère l'instance du lien s'il existe déjà et le créer sinon
     *
     * @param lien le lien à récuperer
     * @return Une instance d'un lien égale à "lien" ou le "lien" lui même
     */
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

    /**
     * récupère l'instance du "noeud" s'il existe déjà et le créer sinon
     *
     * @param noeud le noeud à récuperer
     * @return Une instance d'un Noeud égale à "noeud" ou le "noeud" lui même
     */
    public Noeud getOrCreate(Noeud noeud) {
        for (Noeud node : listNoeud) {
            if (noeud.equals(node)) {
                return node;
            }
        }
        //si pas trouvé c'est qu'il doit être créé
        //donc ajouté à la liste de noeud
        addNoeud(noeud);
        return noeud;
    }

    /**
     *
     * @param noeud le noeud dont on veux vérifier l'éxistence
     * @return true ssi noeud est dans la liste de noeud
     */
    public boolean noeudExist(Noeud noeud) {
        return listNoeud.contains(noeud);
    }

    /**
     * charger un graph à partir d'une String, si il y a des erreurs de formats alors elles seront propagées.
     *
     * @param str La String au format csv qui représente le graph.
     * @throws FormatFileException si il y a des erreurs de fromat
     */
    public void loadFromString(String str) throws FormatFileException { // comprend throw notfilefound
        String fileContent = str.replaceAll("[\n\t\r]", "").trim(); // remove '\n' or '\t' or '\r' et useless space
        System.out.println(fileContent);

        String[] eachNode = fileContent.split(";;");

        Noeud noeudPrincipal;

        Pattern namePattern = Pattern.compile("^[^:]*");

        int lineNumber = 0;
        for (String line : eachNode) {
            try {
                Matcher mainNodeMatch = namePattern.matcher(line);
                if (mainNodeMatch.find()) {
                    String mainNode = mainNodeMatch.group();
                    String[] formatNode = StringFormater.getCoupleFormatCharStr(mainNode);

                    char typeNode = formatNode[0].charAt(0);

                    String nameNode = formatNode[1];

                    noeudPrincipal = new Noeud(Noeud.Type.getType(typeNode), nameNode);

                    noeudPrincipal = getOrCreate(noeudPrincipal);

                    line = line.substring(mainNodeMatch.group().length() + 1); // remove the name from the reste of the String
                } else {
                    throw new FormatFileException(line, lineNumber);
                }
                String[] coupleLienNeoud = line.split(";");
                if (coupleLienNeoud.length != 1 || !coupleLienNeoud[0].isBlank()) {//si il y a un lien (pcq quand pas de lien la taille est de 1 avec élément vide)
                    for (String couple : coupleLienNeoud) {
                        String[] both = couple.split("::");
                        if (both.length != 2) {
                            throw new FormatFileException("[size " + both.length + "] " + couple, lineNumber);
                        }
                        String lienStr = both[0];
                        String neoudStr = both[1];

                        String[] splitLienStr = StringFormater.getCoupleFormatCharStr(lienStr);
                        char type = splitLienStr[0].charAt(0);
                        int distance = Integer.parseInt(splitLienStr[1]);

                        String[] splitNeoudStr = StringFormater.getCoupleFormatCharStr(neoudStr);
                        char typeDst = splitNeoudStr[0].charAt(0);
                        String nameDst = splitNeoudStr[1];
                        Noeud node;
                        node = new Noeud(Noeud.Type.getType(typeDst), nameDst);

                        Lien lien = new Lien(Lien.Type.getType(type), distance, noeudPrincipal, getOrCreate(node));

                        lien = getOrCreate(lien);

                        noeudPrincipal.addLien(lien);
                    }
                }
                addNoeud(noeudPrincipal);
                lineNumber++;
            } catch (FormatFileException e) {
                e.setLine(lineNumber);
                throw e;
            }
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

    /**
     * récuper l'indice du noeud
     *
     * @param noeud le noeud dont on veux l'indice
     * @return l'indice du noeud
     */
    public int getIndiceNoeud(Noeud noeud) {
        int i = 0;
        for (; i < getListNoeud().size(); i++) {
            if ((getListNoeud().get(i)).equals(noeud)) {
                return i;
            }
        }
        return -1; // si il n'existe pas
    }

}
