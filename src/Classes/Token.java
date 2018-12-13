package Classes;

public class Token {

    public String Token;
    public int Tipo;
    public int Linha;

    // lista de possíveis tipos de tokens
    public static final int INVALIDO = -1;
    public static final int RESERVADO = 0;
    public static final int IDENTIFICADOR = 1;
    public static final int OPERADOR = 3;
    public static final int OPERADOR_COMPARADOR = 4;
    public static final int OPERADOR_SINALIZADOR = 7;
    public static final int NUMERO = 5;
    public static final int STRING = 6;
    public static final int STOP = 8;
    public static final int END = 9;

    // constantes de controle de reclassificação
    public static final int LER_PROXIMO = 10;
    public static final int SALVAR_SIMPLES_RESERVADO = 11;
    public static final int SALVAR_SIMPLES_OPERADOR = 12;


}
