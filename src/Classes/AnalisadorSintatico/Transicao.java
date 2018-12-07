package Classes.AnalisadorSintatico;

public class Transicao {


    public int TipoTransiscao;
    public String valorTransicao;
    public int TokenTransicao;
    public Automato TransicaoAutomato;
    public Estado ProximoEstado;

    public static final int LITERAL = 0;
    public static final int TOKEN = 1;
    public static final int REGEX = 2;
    public static final int AUTOMATO = 3;
    public static final int VAZIO = 4;

}
