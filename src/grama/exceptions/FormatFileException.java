package grama.exceptions;

public class FormatFileException extends RuntimeException {

    private String nonConforme;
    private int lineNumber = -1;

    public FormatFileException() {
        super("Le format du fichier n'est pas conforme");
    }
/**
 * ce constructeur permets d'indiquer quelle chaine de caractére n'est pas conforme
 * @param nonConforme 
 */
    public FormatFileException(String nonConforme) {
        super("Le format du fichier n'est pas conforme. partie non conforme : " + nonConforme);
        this.nonConforme = nonConforme;
    }
/**
 * ce constructeur nous permets d'indiquer a l'utilisateur a quelle ligne se trouve précisément l'érreur dans le csv
 * ainsi que la chaine ou il y a une erreur.   
 * @param nonConforme
 * @param lineNumber 
 */
    public FormatFileException(String nonConforme, int lineNumber) {
        super("Le format du fichier n'est pas conforme. à la ligne " + lineNumber + " partie non conforme : " + nonConforme);
        this.nonConforme = nonConforme;
        this.lineNumber = lineNumber;
    }

    public String getNonConforme() {
        return nonConforme;
    }

    public int getLineNumber() {
        return lineNumber;
    }

}
