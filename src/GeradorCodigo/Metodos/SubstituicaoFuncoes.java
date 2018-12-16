package GeradorCodigo.Metodos;

import Classes.AnalisadorSintatico.Identificador;
import Classes.Token;
import Metodos.Geral;
import Sintaxe.TabelaSimbolos;

import java.util.ArrayList;
import java.util.List;

public abstract class SubstituicaoFuncoes {

    public static List<List<Token>> SubstituirFuncoes(List<List<Token>> codigo) {

        Geral.PrintNeutral("\tIniciando substituição...");

        List<List<Token>> ll = new ArrayList<>();

        for (List<Token> l:codigo) {
            ll.add(new ArrayList<>());
            ll.get(ll.size() - 1).addAll(l);
        }

        for (Identificador i: TabelaSimbolos.IDENT_DECLARE) {

            if(i.Tipo == Identificador.FUNCAO) {

                List<Token> l = new ArrayList<>(ll.get(i.Linha));
                l.remove(0);
                l.remove(0);
                l.remove(0);
                l.remove(0);
                l.remove(0);
                l.remove(0);
                l.remove(0);

                for (int z = 0; z < ll.size(); z++) {
                    List<Token> o = ll.get(z);
                    int pos = -1;

                    for(int j = 0; j < o.size(); j++) {
                        Token t = o.get(j);

                        if(t.Token.equals("FN")) {
                            if(!o.get(j-1).Token.equals("DEF")) {
                                if(o.get(j+1).Token.equals(i.Nome)) {
                                    pos = j;
                                    break;
                                }
                            }
                        }
                    }
                    if(pos != -1) {
                        z--;

                        o.remove(pos);
                        o.remove(pos);
                        o.remove(pos);

                        List<Token> arg = new ArrayList<>();
                        int c = 1;
                        do {
                            Token tt = o.get(pos);
                            if(tt.Token.equals("(")) {
                                c++;
                            }
                            else if(tt.Token.equals(")")) {
                                c--;
                            }
                            o.remove(pos);
                            if(c != 0) {
                                arg.add(tt);
                            }

                        }while (c != 0);

                        List<Token> exp_mod = new ArrayList<>(l);

                        for(int k = 0; k < exp_mod.size(); k++) {
                            Token t = exp_mod.get(k);

                            if(t.Token.equals("X") && t.Tipo == Token.IDENTIFICADOR) {
                                exp_mod.remove(k);
                                exp_mod.addAll(k,arg);
                                k = k + arg.size() - 1;
                            }
                        }

                        Token par1 = new Token();
                        par1.Tipo = Token.OPERADOR_SINALIZADOR;
                        par1.Token = "(";

                        Token par2 = new Token();
                        par2.Tipo = Token.OPERADOR_SINALIZADOR;
                        par2.Token = ")";

                        exp_mod.add(0,par1);
                        exp_mod.add(exp_mod.size(),par2);

                        o.addAll(pos,exp_mod);
                    }
                }
                ll.set(i.Linha,new ArrayList<>());
            }
        }
        Geral.PrintNeutral("\tSubstituição concluída.\n");
        return ll;
    }
}
