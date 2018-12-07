package Classes;

public class Token {

    public String Token;
    public int Tipo;

    // lista de possíveis tipos de tokens
    public static final int INVALIDO = -1;
    public static final int PREDEFINIDO = 0;
    public static final int RESERVADO = 1;
    public static final int IDENTIFICADOR = 2;
    public static final int OPERADOR = 3;
    public static final int NUMERO = 4;
    public static final int STRING = 5;
    public static final int SINALIZADOR = 6;
    public static final int STOP = 7;
    public static final int END = 8;

    // constantes de controle de reclassificação
    public static final int LER_PROXIMO = 9;
    public static final int SALVAR_SIMPLES_RESERVADO = 10;
    public static final int SALVAR_SIMPLES_OPERADOR = 11;


}
