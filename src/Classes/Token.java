package Classes;

public class Token {

    public String Token;
    public int Tipo;

    public static final int INVALIDO = -1;
    public static final int LER_PROXIMO = 0;
    public static final int PREDEFINIDO = 1;
    public static final int RESERVADO = 2;
    public static final int IDENTIFICADOR = 3;
    public static final int OPERADOR = 4;

}
