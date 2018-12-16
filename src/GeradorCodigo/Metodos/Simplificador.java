package GeradorCodigo.Metodos;

import Classes.AnalisadorSintatico.Identificador;
import Classes.Token;
import Metodos.Geral;
import Sintaxe.ControleSintaxe;
import Sintaxe.TabelaSimbolos;

import java.util.ArrayList;
import java.util.List;

public abstract class Simplificador {

    public static List<List<Token>> SimplificarCodigo(List<List<Token>> codigo) {

        Geral.PrintNeutral("\tIniciando simplificação...");

        List<List<Token>> ll = new ArrayList<>();

        for (List<Token> l:codigo) {
            ll.add(new ArrayList<>());
            ll.get(ll.size() - 1).addAll(l);
        }

        int mudancas = 0;
        do {

            for (Identificador i: TabelaSimbolos.IDENT_DECLARE) {

                if(i.Tipo == Identificador.VARIAVEL || i.Tipo == Identificador.FUNCAO) {

                    int refs = 0;

                    for (Identificador i2:TabelaSimbolos.IDENTIFICADORES) {
                        if(i.Tipo == i2.Tipo && i.Nome.equals(i2.Nome)) {
                            refs++;
                        }
                    }
                    mudancas = 0;
                    if(refs == 0) {
                        mudancas = 1;
                        ll.set(i.Linha, new ArrayList<>());
                        TabelaSimbolos.IDENT_DECLARE.remove(i);
                        break;
                    }
                }

            }
        } while (mudancas != 0);

        if(ControleSintaxe.hasDATA == 1 && !ControleSintaxe.hasREAD) {
            for (List<Token> l:ll) {
                boolean removed = false;
                for (Token t:l) {
                    if(t.Token.equals("DATA")) {
                        ll.set(t.Linha,new ArrayList<>());
                        removed = true;
                        break;
                    }
                }
                if(removed) {
                    break;
                }
            }
        }

        for (List<Token> l:ll) {
            for (Token t:l) {
                if(t.Token.equals("REM")) {
                    ll.set(t.Linha,new ArrayList<>());
                    break;
                }
            }
        }

        Geral.PrintNeutral("\tSimplificação concluída.\n");

        return ll;
    }
}
