package Sintaxe;

import Classes.AnalisadorSintatico.Constante;
import Classes.AnalisadorSintatico.Variavel;
import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class TabelaSimbolos {

    public static List<Variavel> Variaveis = new ArrayList<>();
    public static List<Constante> Constantes = new ArrayList<>();
    public static List<Variavel> Labels = new ArrayList<>();

    public static void AdicionarVariavel(Token t,boolean declaracao){

        Variaveis.add(new Variavel(t,declaracao));
    }

    public static void AdicionarConstante(Token t){

        for (Constante c:Constantes) {
            if(c.Valor == Double.parseDouble(t.Token)) {
                c.Referencias.add(t.Linha);
                return;
            }
        }
        Constantes.add(new Constante(t));
    }

    public static void AdicionarLabel(Token t, boolean declaracao) {
        Labels.add(new Variavel(t,declaracao));
    }

}
