package Motores;

import Classes.Evento;
import Classes.Memoria;
import Classes.Token;
import Metodos.CategorizadorToken;

import java.util.ArrayList;

public abstract class Nivel6 {

    private static int NumeroLinha = 0;
    private static int NumeroToken = 0;

    private static Token ultimoToken;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.AcessoMotor(6).ObterProximoEvento(tempo);

        if(e.Tipo == Evento.RECLASSIFICAR_TOKENS) {

            Token t = Memoria.Tokens.get(NumeroLinha).get(NumeroToken);

            if(Memoria.TokensReclassificados.size() < NumeroLinha + 1) {
                Memoria.TokensReclassificados.add(new ArrayList<>());
            }

            if(t != null) {

                NumeroToken++;

                int rec;

                if(ultimoToken == null) {
                    rec = CategorizadorToken.RecategorizarToken(t);
                    SalvarToken(t,rec);
                }
                else {
                    rec = CategorizadorToken.RecategorizarToken(ultimoToken,t);
                    Token t1 = new Token();
                    t1.Token = ultimoToken.Token.concat(t.Token);
                    SalvarToken(t1,rec);

                    if(rec == Token.SALVAR_SIMPLES_OPERADOR || rec == Token.SALVAR_SIMPLES_RESERVADO) {
                        rec = CategorizadorToken.RecategorizarToken(t);
                        SalvarToken(t,rec);
                    }

                }


                if(NumeroToken == Memoria.Tokens.get(NumeroLinha).size()) {

                    if(ultimoToken != null) {
                        Token dummy = new Token();
                        dummy.Token = "";

                        int r = CategorizadorToken.RecategorizarToken(ultimoToken,dummy);
                        SalvarToken(ultimoToken,r);
                    }

                    NumeroLinha++;
                    NumeroToken = 0;
                }
            }
        }
    }

    private static void SalvarToken(Token t, int tipo) {

        Token tr = new Token();
        tr.Token = t.Token.toUpperCase();

        switch (tipo){

            case Token.RESERVADO:

                if(tr.Token.equals("END")) {
                    tr.Tipo = Token.END;
                }
                else if(tr.Token.equals("STOP")){
                    tr.Tipo = Token.STOP;
                }
                else {
                    tr.Tipo = Token.RESERVADO;
                }
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                ultimoToken = null;
                break;
            case Token.STRING:
                tr.Tipo = Token.STRING;
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                ultimoToken = null;
                break;
            case Token.OPERADOR:
                tr.Tipo = Token.OPERADOR;
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                ultimoToken = null;
                break;
            case Token.PREDEFINIDO:
                tr.Tipo = Token.PREDEFINIDO;
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                ultimoToken = null;
                break;
            case Token.SALVAR_SIMPLES_OPERADOR:
                tr.Token = ultimoToken.Token.toUpperCase();
                tr.Tipo = Token.OPERADOR;
                ultimoToken = null;
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                break;
            case Token.SALVAR_SIMPLES_RESERVADO:
                tr.Token = ultimoToken.Token.toUpperCase();
                tr.Tipo = Token.RESERVADO;
                ultimoToken = null;
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                break;
            case Token.IDENTIFICADOR:
                tr.Tipo = Token.IDENTIFICADOR;
                ultimoToken = null;
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                break;
            case Token.NUMERO:
                tr.Tipo = Token.NUMERO;
                ultimoToken = null;
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                break;
            case Token.SINALIZADOR:
                tr.Tipo = Token.SINALIZADOR;
                ultimoToken = null;
                Memoria.TokensReclassificados.get(NumeroLinha).add(tr);
                break;
            case Token.LER_PROXIMO:
                ultimoToken = t;
                break;
            default:
                ControleMotores.ErroDeLeitura = true;

                if(ultimoToken != null) {
                    ControleMotores.DescricaoErro = String.format("Erro: Reclassificação do Token \"%s\" (linha %d, %d), interrompendo análise.\n",ultimoToken.Token,NumeroLinha,NumeroToken - 2);
                }
                else {
                    ControleMotores.DescricaoErro = String.format("Erro: Reclassificação do Token \"%s\" (linha %d, %d), interrompendo análise.\n",t.Token,NumeroLinha,NumeroToken - 1);
                }

                ultimoToken = null;
                break;
        }
    }
}
