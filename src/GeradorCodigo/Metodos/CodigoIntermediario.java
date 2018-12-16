package GeradorCodigo.Metodos;

import Classes.GeradorCodigo.LinhaCodInter;
import Classes.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class CodigoIntermediario {

    public static List<LinhaCodInter> NovasLinhas = new ArrayList<>();

    private static Stack<LinhaCodInter> Loops = new Stack<>();

    public static void GerarLinha(List<Token> linha) {

        LinhaCodInter l = new LinhaCodInter();

        List<Token> t;

        if(linha.size() == 0) {
            return;
        }

        if(linha.get(0).Tipo == Token.IDENTIFICADOR) {
            l.Label = linha.get(0).Token;
            linha.remove(0);
            linha.remove(0);
        }

        switch (linha.get(0).Token) {
            case "LET":
                l.Tipo = LinhaCodInter.ASSIGN;
                l.Variaveis.add(linha.get(1));
                linha.remove(0);
                linha.remove(0);
                linha.remove(0);

                l.Expressoes.add(EXPtoRPN.ExpToRPN(linha));
                NovasLinhas.add(l);
                break;
            case "PRINT":
                l.Tipo = LinhaCodInter.PRINT;
                linha.remove(0);
                boolean fim = false;
                while(!fim) {
                    l.Variaveis.add(linha.get(0));
                    linha.remove(0);
                    if(linha.size() == 0) {
                        fim = true;
                    }
                    else {
                        linha.remove(0);
                    }
                }
                NovasLinhas.add(l);
                break;
            case "FOR":
                l.Tipo = LinhaCodInter.FOR;
                linha.remove(0);
                l.Variaveis.add(linha.get(0));
                linha.remove(0);
                linha.remove(0);

                t = new ArrayList<>();

                while(!linha.get(0).Token.equals("TO")) {
                    t.add(linha.get(0));
                    linha.remove(0);
                }
                l.Expressoes.add(EXPtoRPN.ExpToRPN(t));
                linha.remove(0);

                t = new ArrayList<>();

                while(!linha.get(0).Token.equals("STEP")) {
                    t.add(linha.get(0));
                    linha.remove(0);
                }
                l.Expressoes.add(EXPtoRPN.ExpToRPN(t));
                linha.remove(0);

                t = new ArrayList<>();

                while(linha.size() > 0) {
                    t.add(linha.get(0));
                    linha.remove(0);
                }
                l.Expressoes.add(EXPtoRPN.ExpToRPN(t));

                Loops.push(l);
                NovasLinhas.add(l);
                break;
            case "NEXT":
                l.Tipo = LinhaCodInter.NEXT;
                l.Variaveis.add(linha.get(1));

                LinhaCodInter x = Loops.pop();
                l.Expressoes.add(x.Expressoes.get(2));
                NovasLinhas.add(l);
                break;
            case "IF":
                l.Tipo = LinhaCodInter.IF;
                linha.remove(0);
                l.Variaveis.add(linha.get(0));
                linha.remove(0);
                l.Variaveis.add(linha.get(0));

                t = new ArrayList<>();

                while(!linha.get(0).Token.equals("THEN")) {
                    t.add(linha.get(0));
                    linha.remove(0);
                }
                l.Expressoes.add(EXPtoRPN.ExpToRPN(t));
                linha.remove(0);
                l.Variaveis.add(linha.get(0));
                NovasLinhas.add(l);
                break;
            case "GOTO":
                l.Tipo = LinhaCodInter.GOTO;
                l.Variaveis.add(linha.get(1));
                NovasLinhas.add(l);
                break;
            case "GOSUB":
                l.Tipo = LinhaCodInter.GOSUB;
                l.Variaveis.add(linha.get(1));
                NovasLinhas.add(l);
                break;
            case "RETURN":
                l.Tipo = LinhaCodInter.RETURN;
                NovasLinhas.add(l);
                break;
            case "DATA":
                l.Tipo = LinhaCodInter.DATA;
                linha.remove(0);

                while (linha.size() > 0) {
                    Token tt = linha.get(0);

                    if(tt.Token.equals("-")) {
                        Token t2 = linha.get(1);
                        t2.Token = "-".concat(t2.Token);
                        l.Variaveis.add(t2);
                        linha.remove(0);
                    }
                    else {
                        l.Variaveis.add(tt);
                    }
                    linha.remove(0);
                }
                NovasLinhas.add(l);
                break;
            case "READ":
                l.Tipo = LinhaCodInter.READ;
                linha.remove(0);
                boolean f = false;
                while(!f) {
                    l.Variaveis.add(linha.get(0));
                    linha.remove(0);
                    if(linha.size() == 0) {
                        f = true;
                    }
                    else {
                        linha.remove(0);
                    }
                }
                NovasLinhas.add(l);
                break;
        }
    }

}
