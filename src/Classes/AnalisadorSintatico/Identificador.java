package Classes.AnalisadorSintatico;

import Classes.Token;

public class Identificador {

    public String Nome;
    public int Linha;
    public int Tipo;

    public Identificador(Token t, int tipo) {

        Nome = t.Token;
        Linha = t.Linha;
        Tipo = tipo;
    }

    public static final int VARIAVEL = 0;
    public static final int LABEL = 1;
    public static final int FUNCAO = 2;


}
