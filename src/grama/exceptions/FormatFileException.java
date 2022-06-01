package grama.exceptions;

public class FormatFileException extends RuntimeException {

    private String nonConforme;
    private int lineNumber = -1;

    public FormatFileException() {
        super("Le format du fichier n'est pas conforme");
    }

    public FormatFileException(String nonConforme) {
        super("Le format du fichier n'est pas conforme. partie non conforme : " + nonConforme);
        this.nonConforme = nonConforme;
    }

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
