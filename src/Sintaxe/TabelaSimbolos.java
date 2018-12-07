package Sintaxe;

import Classes.Simbolo;

import java.util.ArrayList;
import java.util.List;

public abstract class TabelaSimbolos {

    public static List<Simbolo> Variaveis;
    public static List<Simbolo> Constantes;
    public static List<Simbolo> Labels;

    public static void InicializaTabelaSimbolos() {
        Variaveis = new ArrayList<>();
        Constantes = new ArrayList<>();
        Labels = new ArrayList<>();
    }

}
