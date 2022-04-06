package grama.exceptions;

public class MauvaisType extends RuntimeException
{
        public MauvaisType()
        {
                super("Le type entrée ne corresponds à aucun des types attendus");
        }




}
