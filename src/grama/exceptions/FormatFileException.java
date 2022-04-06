package grama.exceptions;

public class FormatFileException extends RuntimeException {
    public FormatFileException()
    {
        super("Le format du fichier n'est pas conforme");
    }
}
