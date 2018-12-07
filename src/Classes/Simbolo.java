package Classes;

public class Simbolo {

    public String Nome;
    public int LinhaDeclaracao;
    public int NumeroToken;
    public int TipoReferencia;

    public Simbolo(String nome, int linha, int numero, int tipo) {
        Nome = nome;
        LinhaDeclaracao = linha;
        NumeroToken = numero;
        TipoReferencia = tipo;
    }


    private static final int DECLARACAO = 0;
    private static final int REFERENCIA = 1;

}
