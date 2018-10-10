package Classes;

public class Simbolo {

    public char Simbolo;
    public int Tipo;
    public boolean Util;

    public Simbolo(){}

    public Simbolo(char simbolo, int tipo) {

        this.Simbolo = simbolo;
        this.Tipo = tipo;
        this.Util = true;
    }

    public static final int S_LETRA_MIN = 0;
    public static final int S_LETRA_MAI = 1;
    public static final int S_DIGITO = 2;
    public static final int S_ESPACO = 3;
    public static final int S_ESPECIAL = 4;
    public static final int S_CONTROLE = 5;
    public static final int S_INVALIDO = -1;

}
