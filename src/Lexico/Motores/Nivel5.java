package Lexico.Motores;

import Classes.AnalisadorLexico.Evento;
import Classes.Memoria;
import Classes.AnalisadorLexico.Caractere;
import Classes.Token;

import java.util.ArrayList;

public abstract class Nivel5 {

    private static int NumeroLinha = 0;
    private static int NumeroChar = 0;
    private static StringBuilder TokenBuilder = new StringBuilder();

    private static boolean inString = false;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.AcessoMotor(5).ObterProximoEvento(tempo);

        if(e.Tipo == Evento.CLASSIFICAR_TOKENS) {

            Caractere s = Memoria.Caracteres.get(NumeroLinha).get(NumeroChar);

            if(Memoria.Tokens.size() < NumeroLinha + 1) {
                Memoria.Tokens.add(new ArrayList<>());
            }

            if(s != null) {

                Token token = new Token();
                token.Linha = NumeroLinha;
                Evento e2;
                NumeroChar++;
                switch(s.Tipo) {

                    case Caractere.OUTRO:
                        if(inString) {
                            TokenBuilder.append(s.Caractere);
                        }
                        else {
                            ControleMotores.ErroDeLeitura = true;
                            ControleMotores.DescricaoErro = String.format("Erro: Criação de Tokens: Caractere \"%c\" não é válido (%d,%d), interrompendo análise.\n",
                                    s.Caractere,NumeroLinha,NumeroChar);
                        }
                        break;

                    case Caractere.LETRA:
                        TokenBuilder.append(s.Caractere);
                        break;

                    case Caractere.DIGITO:
                        TokenBuilder.append(s.Caractere);
                        break;

                    case Caractere.ESPACO:

                        if(inString) {
                            TokenBuilder.append(s.Caractere);
                        }
                        else {
                            token.Token = TokenBuilder.toString();

                            if(!token.Token.equals("")) {
                                Memoria.Tokens.get(NumeroLinha).add(token);
                                TokenBuilder = new StringBuilder();
                                e2 = new Evento(Evento.RECLASSIFICAR_TOKENS,tempo,tempo+1);
                                ControleMotores.AcessoMotor(6).AdicionarEvento(e2);
                            }
                        }
                        break;

                    case Caractere.ESPECIAL:

                        if (s.Caractere == '\"') {
                            if(inString) {
                                TokenBuilder.append(s.Caractere);
                                token.Token = TokenBuilder.toString();
                                TokenBuilder = new StringBuilder();
                                inString = false;
                            }
                            else {
                                token.Token = TokenBuilder.toString();
                                TokenBuilder = new StringBuilder();
                                inString = true;
                                TokenBuilder.append(s.Caractere);
                            }
                            if(!token.Token.equals("")) {
                                Memoria.Tokens.get(NumeroLinha).add(token);
                                e2 = new Evento(Evento.RECLASSIFICAR_TOKENS,tempo,tempo+1);
                                ControleMotores.AcessoMotor(6).AdicionarEvento(e2);
                            }
                        }
                        else if(inString) {
                            TokenBuilder.append(s.Caractere);
                        }
                        else {

                            token.Token = TokenBuilder.toString();
                            if(!token.Token.equals("")) {
                                Memoria.Tokens.get(NumeroLinha).add(token);
                                TokenBuilder = new StringBuilder();
                                e2 = new Evento(Evento.RECLASSIFICAR_TOKENS,tempo,tempo+1);
                                ControleMotores.AcessoMotor(6).AdicionarEvento(e2);
                            }

                            Token token2 = new Token();
                            token2.Linha = NumeroLinha;
                            token2.Token = String.format("%c",s.Caractere);
                            Memoria.Tokens.get(NumeroLinha).add(token2);
                            e2 = new Evento(Evento.RECLASSIFICAR_TOKENS,tempo,tempo+1);
                            ControleMotores.AcessoMotor(6).AdicionarEvento(e2);
                        }

                        break;

                    case Caractere.CONTROLE:
                        token.Token = TokenBuilder.toString();

                        if(!token.Token.equals("")) {
                            Memoria.Tokens.get(NumeroLinha).add(token);
                            TokenBuilder = new StringBuilder();
                            e2 = new Evento(Evento.RECLASSIFICAR_TOKENS,tempo,tempo+1);
                            ControleMotores.AcessoMotor(6).AdicionarEvento(e2);
                        }
                        break;
                }

                if(NumeroChar == Memoria.Caracteres.get(NumeroLinha).size()) {
                    NumeroLinha++;
                    NumeroChar = 0;
                }
            }
        }
    }
}
