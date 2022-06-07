package grama.exceptions;

public class MauvaisTypeException extends FormatFileException {

    public MauvaisTypeException() {
        super("Le type entrée ne corresponds à aucun des types attendus");
    }
}
