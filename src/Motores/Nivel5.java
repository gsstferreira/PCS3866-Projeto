package Motores;

import Classes.Evento;
import Classes.Memoria;
import Classes.Simbolo;
import Classes.Token;

public abstract class Nivel5 {

    private static int NumeroLinha = 0;
    private static int NumeroChar = 0;
    private static StringBuilder TokenBuilder = new StringBuilder();

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.MotorNivel_5.ObterProximoEvento(tempo);

        if(e.Tipo == Evento.EV_CLASSIFICAR_TOKENS) {

            Simbolo s = Memoria.Simbolos.get(NumeroLinha).get(NumeroChar);

            if(s != null) {

                Token token = new Token();

                NumeroChar++;
                switch(s.Tipo) {
                    case Simbolo.S_LETRA_MIN:
                        TokenBuilder.append(s.Simbolo);
                        break;

                    case Simbolo.S_LETRA_MAI:
                        TokenBuilder.append(s.Simbolo);
                        break;

                    case Simbolo.S_DIGITO:
                        TokenBuilder.append(s.Simbolo);
                        break;

                    case Simbolo.S_ESPACO:
                        token.Token = TokenBuilder.toString();

                        if(!token.Token.equals("")) {
                            Memoria.Tokens.get(NumeroLinha).add(token);
                            TokenBuilder = new StringBuilder();
                        }
                        break;

                    case Simbolo.S_ESPECIAL:
                        token.Token = TokenBuilder.toString();

                        if(!token.Token.equals("")) {
                            Memoria.Tokens.get(NumeroLinha).add(token);
                            TokenBuilder = new StringBuilder();
                        }

                        Token token2 = new Token();
                        token2.Token = String.format("%c",s.Simbolo);
                        Memoria.Tokens.get(NumeroLinha).add(token);
                        break;

                    case Simbolo.S_CONTROLE:
                        token.Token = TokenBuilder.toString();

                        if(!token.Token.equals("")) {
                            Memoria.Tokens.get(NumeroLinha).add(token);
                            TokenBuilder = new StringBuilder();
                        }
                        break;
                }

                if(NumeroChar == Memoria.Simbolos.get(NumeroLinha).size()) {
                    NumeroLinha++;
                    NumeroChar = 0;
                }
            }
        }
    }
}
