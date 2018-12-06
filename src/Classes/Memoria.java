package Classes;

import java.util.ArrayList;
import java.util.List;


public abstract class   Memoria {

    // código separado em linhas
    public static List<String> Linhas;

    // linhas separadas em caracteres (símbolos)
    public static List<List<Simbolo>> Simbolos;

    // símbolos agrupados em tokens, por linha de código
    public static List<List<Token>> Tokens;

    // tokens reclassificados, por linha de código
    public static List<List<Token>> TokensReclassificados;

    // inicialização das listas da memória
    public static void InicializarMemoria() {

        Linhas = new ArrayList<>();
        Simbolos = new ArrayList<>();
        Tokens = new ArrayList<>();
        TokensReclassificados = new ArrayList<>();
    }
}
