import Classes.Memoria;
import Classes.Token;
import Metodos.CategorizadorToken;
import Metodos.Geral;
import Motores.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Main {

    private static int ContadorTempo;

    public static void main(String[] args) {

        Memoria.InicializarMemoria();

        ContadorTempo = 0;

        try {
            ControleMotores.InicializaMotores(new FileInputStream("TestesTxt/Teste.txt"));
        } catch (IOException e) {
            System.out.print("Arquivo 'teste.txt'não foi encontrado.\n");
            return;
        }

        CategorizadorToken.InicializaRecategoriador();

        while (ControleMotores.ContinuarAnalise()) {

            Nivel1.RealizarEvento(ContadorTempo);

            if(ControleMotores.ErroDeLeitura) {
               break;
            }

            Nivel2.RealizarEvento(ContadorTempo);

            if(ControleMotores.ErroDeLeitura) {
                break;
            }

            Nivel3.RealizarEvento(ContadorTempo);

            if(ControleMotores.ErroDeLeitura) {
                break;
            }

            Nivel4.RealizarEvento(ContadorTempo);

            if(ControleMotores.ErroDeLeitura) {
                break;
            }

            Nivel5.RealizarEvento(ContadorTempo);

            if(ControleMotores.ErroDeLeitura) {
                break;
            }


            ContadorTempo++;
        }

        while(ControleMotores.ContinuarAnaliseNivel6()) {

            Nivel6.RealizarEvento(ContadorTempo);

            if(ControleMotores.ErroDeLeitura) {
                break;
            }

            ContadorTempo++;
        }

        for (List<Token> l:Memoria.TokensReclassificados) {
            for (Token t:l) {
                switch (t.Tipo){

                    case Token.IDENTIFICADOR:
                        Geral.PrintColor(t.Token,Geral.ANSI_YELLOW);
                        break;
                    case Token.NUMERO:
                        Geral.PrintColor(t.Token,Geral.ANSI_GREEN);
                        break;
                    case Token.RESERVADO:
                        Geral.PrintColor(t.Token.concat(" "),Geral.ANSI_CYAN);
                        break;
                    case  Token.OPERADOR:
                        Geral.PrintColor(String.format(" %s ",t.Token),Geral.ANSI_RED);
                        break;
                    case Token.CONTROLE:
                        Geral.PrintColor(t.Token,Geral.ANSI_RESET);
                        break;
                    case Token.PREDEFINIDO:
                        Geral.PrintColor(t.Token.concat(" "),Geral.ANSI_CYAN);
                        break;
                    case Token.STOP:
                        Geral.PrintColor(" ".concat(t.Token),Geral.ANSI_BLUE);
                        break;
                    case Token.END:
                        Geral.PrintColor(t.Token,Geral.ANSI_BLUE);
                        break;
                    default:
                        Geral.PrintColor(t.Token,Geral.ANSI_RESET);
                        break;
                }
            }
            System.out.print("\n");
        }
    }
}
