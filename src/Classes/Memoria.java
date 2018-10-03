package Classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Memoria {

    public static List<String> Linhas;
    public static List<List<Simbolo>> Simbolos;
    public static List<List<Token>> Tokens;
    public static List<List<Token>> TokensReclassificados;

    public static void InializarMemoria() {

        Linhas = new ArrayList<>();
        Simbolos = new ArrayList<>();
        Tokens = new ArrayList<>();
        TokensReclassificados = new ArrayList<>();

    }

}
