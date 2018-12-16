package Sintaxe;

import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class AdicaoRedundancia {

    public static List<List<Token>> AdicionarRedundanciaZero(List<List<Token>> codigo) {

        List<List<Token>> listaFinal = new ArrayList<>();
        int i = 0;

        for (List<Token> l:codigo) {

            listaFinal.add(new ArrayList<>());

            for(int j = 0; j < l.size(); j++) {

                Token t1 = l.get(j);

//                if(t1.Token.equals("DATA")) {
//                    listaFinal.get(i).addAll(l);
//                    break;
//                }

                if(!",=(".contains(t1.Token) && !t1.Token.equals("STEP")) {
                    listaFinal.get(i).add(t1);
                }
                else {
                    Token t2 = l.get(j+1);

                    if(t2.Token.equals("-")) {
                        Token tZero = new Token();
                        tZero.Token = "0";
                        tZero.Tipo = Token.NUMERO;
                        tZero.Linha = t1.Linha;

                        listaFinal.get(i).add(t1);
                        listaFinal.get(i).add(tZero);
                    }
                    else {
                        listaFinal.get(i).add(t1);
                    }
                }
            }
            i++;
        }

        return listaFinal;
    }

    public static List<List<Token>> AdcionarRedundanciaStep(List<List<Token>> codigo) {

        List<List<Token>> listaFinal = new ArrayList<>();
        int i = 0;

        for (List<Token> l:codigo) {

            listaFinal.add(new ArrayList<>());
            listaFinal.get(i).addAll(l);
            
            boolean _for = false;
            boolean _step = false;

            for (Token t:l) {

                if(t.Token.equals("FOR")) {
                    _for = true;
                }
                else if(t.Token.equals("STEP")) {
                    _step = true;
                }
            }

            if(_for && !_step) {
                Token t1 = new Token();
                t1.Token = "STEP";
                t1.Tipo = Token.RESERVADO;
                t1.Linha = i;

                Token t2 = new Token();
                t2.Token = "1";
                t2.Tipo = Token.NUMERO;
                t2.Linha = i;

                listaFinal.get(i).add(t1);
                listaFinal.get(i).add(t2);
            }
            
            i++;
        }

        return listaFinal;
    }
}
