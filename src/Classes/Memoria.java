package Classes;

import Classes.AnalisadorLexico.Caractere;

import java.util.ArrayList;
import java.util.List;


public abstract class   Memoria {

    // código separado em linhas
    public static List<String> Linhas;

    // linhas separadas em caracteres (símbolos)
    public static List<List<Caractere>> Caracteres;

    // símbolos agrupados em tokens, por linha de código
    public static List<List<Token>> Tokens;

    // tokens reclassificados, por linha de código
    public static List<List<Token>> TokensReclassificados;

    // inicialização das listas da memória
    public static void InicializarMemoria() {

        Linhas = new ArrayList<>();
        Caracteres = new ArrayList<>();
        Tokens = new ArrayList<>();
        TokensReclassificados = new ArrayList<>();
    }


}
