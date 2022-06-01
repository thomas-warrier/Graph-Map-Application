package grama.exceptions;

public class FormatFileException extends RuntimeException {
    public FormatFileException()
    {
        super("Le format du fichier n'est pas conforme");
    }
    public FormatFileException(String nonConforme)
    {
        super("Le format du fichier n'est pas conforme. partie non conforme : " + nonConforme);
    }
    
    public FormatFileException(String nonConforme, int lineNumber){
        this("Le format du fichier n'est pas conforme. partie non conforme : " + nonConforme + " à la ligne " + lineNumber);
    }
}
