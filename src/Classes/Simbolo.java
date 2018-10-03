package Classes;

public class Simbolo {

    public char Simbolo;
    public int Tipo;
    public boolean Util;

    public Simbolo(char simbolo, int tipo) {

        this.Simbolo = simbolo;
        this.Tipo = tipo;
        this.Util = true;
    }

    public static final int S_LETRA = 0;
    public static final int S_DIGITO = 1;
    public static final int S_ESPECIAL = 2;
    public static final int S_CONTROLE = 3;

}
