package Classes.AnalisadorSintatico;

import Classes.Token;

public class Variavel {

    public String Nome;
    public int Linha;
    public boolean Declaracao;

    public  Variavel(Token t, boolean declaracao) {

        Nome = t.Token;
        Linha = t.Linha;
        Declaracao = declaracao;
    }
}
