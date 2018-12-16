package Sintaxe;

import Classes.AnalisadorSintatico.Identificador;
import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class TabelaSimbolos {

    public static List<Identificador> IDENTIFICADORES = new ArrayList<>();
    public static List<Identificador> IDENT_DECLARE = new ArrayList<>();

    private static List<Identificador> FOR_NEXT = new ArrayList<>();

    private static boolean AdicionarIdentificador(Token t,boolean declaracao, int tipo){

        if(declaracao) {
            switch (tipo) {
                case Identificador.VARIAVEL:

                    for (Identificador i:IDENT_DECLARE) {
                        if(i.Tipo == Identificador.VARIAVEL && i.Nome.equals(t.Token)) {
                            if(i.Linha < t.Linha) {
                                IDENTIFICADORES.add(new Identificador(t, Identificador.VARIAVEL));
                                return true;
                            }
                        }
                    }

                    IDENT_DECLARE.add(new Identificador(t, Identificador.VARIAVEL));
                    return true;
                default:
                    for (Identificador i:IDENT_DECLARE) {
                        if(i.Tipo == tipo && i.Nome.equals(t.Token)) {
                            ControleSintaxe.DescricaoErro = String.format("Erro: Declaração de \"%s\" (linha %d), já foi declarado em (%d)",t.Token,t.Linha,i.Linha);
                            return false;
                        }
                    }
                    IDENT_DECLARE.add(new Identificador(t, tipo));
                    return true;
            }
        }
        else {
            IDENTIFICADORES.add(new Identificador(t, tipo));
            return true;
        }
    }

    public static boolean TabelaLabels(List<List<Token>> codigo) {

        boolean ok = true;

        for (List<Token> l:codigo) {

            Token t = l.get(0);

            if(t.Tipo == Token.IDENTIFICADOR) {
                ok = AdicionarIdentificador(t,true,Identificador.LABEL);
                l.remove(0);
                l.remove(0);

                String s = l.get(0).Token;

                if(s.equals("DATA") || s.equals("DEF") || s.equals("RETURN") || s.equals("END") || s.equals("REM") || s.equals("NEXT")) {
                    ok = false;
                    ControleSintaxe.DescricaoErro = String.format("Erro: comando \"%s\" não permite uso de labels (linha %d)",s,t.Linha);
                }

            }

            if(!ok) {
                return false;
            }

            t = l.get(0);

            if(t.Token.equals("GOTO") || t.Token.equals("GOSUB")) {
                t = l.get(1);
                ok = AdicionarIdentificador(t,false,Identificador.LABEL);
                l.remove(1);
            }
            else if(t.Token.equals("IF")) {
                t = l.get(l.size() - 1);

                if(t.Tipo != Token.STOP) {
                    ok = AdicionarIdentificador(t,false,Identificador.LABEL);
                    l.remove(l.size() - 1);
                }
            }

            if(!ok) {
                return false;
            }
        }
        return true;
    }

    public static boolean TabelaFuncoes(List<List<Token>> codigo) {

        boolean ok = true;

        for (List<Token> l:codigo) {

            Token t = l.get(0);

            if(t.Token.equals("DEF")) {

                t = l.get(2);

                ok = AdicionarIdentificador(t,true,Identificador.FUNCAO);
                l.remove(0);
                l.remove(0);
                l.remove(0);
                l.remove(0);
                l.remove(0);
                l.remove(0);

                for (int j = 0; j < l.size(); j ++) {

                    Token tt = l.get(j);

                    if(tt.Tipo == Token.IDENTIFICADOR && tt.Token.equals("X")) {
                        l.remove(j);
                        j--;
                    }
                }
            }

            if(!ok) {
                return false;
            }

            for(int j = 0; j < l.size();j++) {

                Token tt = l.get(j);

                if(tt.Token.equals("FN")) {
                    tt = l.get(j+1);
                    ok = AdicionarIdentificador(tt,false,Identificador.FUNCAO);
                    l.remove(j+1);
                }
            }

            if(!ok) {
                return false;
            }

        }
        return true;
    }

    public static boolean TabelaVariaveis(List<List<Token>> codigo) {

        boolean ok = true;

        for (List<Token> l:codigo) {

            Token t = l.get(0);

//            if(t.Token.equals("DATA")) {
//                ControleSintaxe.hasDATA++;
//            }
//            else if(t.Token.equals("READ")) {
//                ControleSintaxe.hasREAD = true;
//                ControleSintaxe.LinhasREAD = ControleSintaxe.LinhasREAD.concat(String.format("%d ",t.Linha));
//            }

            if(t.Token.equals("LET") || t.Token.equals("FOR")) {
                Token tt = l.get(1);
                ok = AdicionarIdentificador(tt,true,Identificador.VARIAVEL);
                l.remove(0);
                l.remove(0);

                if(t.Token.equals("FOR")) {
                    FOR_NEXT.add(new Identificador(tt,Identificador.VARIAVEL));
                }

            }
            if(!ok) {
                return false;
            }

            t = l.get(0);

            if(t.Token.equals("NEXT")) {
                t = l.get(1);

                for (Identificador i:FOR_NEXT) {
                    if(i.Nome.equals(t.Token)) {
                        if(i.Linha < t.Linha) {
                            FOR_NEXT.remove(i);
                            break;
                        }
                    }
                }
            }

            else {
                for (Token tt:l) {
                    if(tt.Tipo == Token.IDENTIFICADOR) {
                        ok = AdicionarIdentificador(tt,false,Identificador.VARIAVEL);

                    }
                }
            }

            if(!ok) {
                return false;
            }
        }
        if(FOR_NEXT.size() > 0) {
            Identificador i = FOR_NEXT.get(0);
            ControleSintaxe.DescricaoErro = String.format("Erro: laço declarado em (%d) não tem seu respctivo limitador (\"NEXT\")",i.Linha);
            return false;
        }
        return true;
    }

    public static boolean PreencherTabela(List<List<Token>> codigo) {

        List<List<Token>> list = new ArrayList<>();

        boolean ok;
        int i = 0;

        for (List<Token> l:codigo) {
            list.add(new ArrayList<>());
            list.get(i).addAll(l);
            i++;
        }

        // Buscando labels
        ok = TabelaLabels(list);

        if(!ok) {
            return false;
        }

        // Buscando Funções
        ok = TabelaFuncoes(list);

        if(!ok) {
            return false;
        }

        // Buscando Funções
        ok = TabelaVariaveis(list);

        if(!ok) {
            return false;
        }

        return true;
    }

    public static boolean VerificarReferencias() {

        for (Identificador i:IDENTIFICADORES) {

            boolean ok = false;

            int labelCount = 0;
            int funcCount = 0;

            boolean funcRecursao = false;

loop:       for (Identificador i2:IDENT_DECLARE) {

                if(i.Nome.equals(i2.Nome)) {

                    if(i.Tipo != i2.Tipo) {
                        ControleSintaxe.DescricaoErro = String.format("Erro: Identificador \"%s\" (linha %d) não é o tipo esperado - declaração na linha %d",
                                i.Nome,i.Linha,i2.Linha);
                        return false;
                    }
                    else {
                        switch (i.Tipo) {
                            case Identificador.VARIAVEL:
                                if(i2.Linha < i.Linha) {
                                    ok = true;
                                    break loop;
                                }
                                break;
                            case Identificador.LABEL:
                                labelCount++;
                                ok = true;
                                break;
                            case Identificador.FUNCAO:
                                funcCount++;
                                ok = true;

                                if(i.Linha == i2.Linha) {
                                    funcRecursao = true;
                                }

                                break;

                        }
                    }
                }
            }

            if(!ok) {
                ControleSintaxe.DescricaoErro = String.format("Erro: Identificador \"%s\" (linha %d) não foi declarado",i.Nome,i.Linha);
                return false;
            }
            else if(labelCount > 1 || funcCount > 1) {
                ControleSintaxe.DescricaoErro = String.format("Erro: Identificador \"%s\" (linha %d) tem mais de uma declaração",i.Nome,i.Linha);
                return false;
            }
            else if(funcRecursao) {
                ControleSintaxe.DescricaoErro = String.format("Erro: Função \"%s(X)\" (linha %d) é referenciada na própria declaração",i.Nome,i.Linha);
                return false;
            }
        }
        return true;
    }
}
