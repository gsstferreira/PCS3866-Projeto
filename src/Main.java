import Classes.Memoria;
import Classes.Simbolo;
import Classes.Token;
import Motores.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Main {

    private static int ContadorTempo;

    public static void main(String[] args) {

        Memoria.InializarMemoria();

        ContadorTempo = 0;

        try {
            ControleMotores.InicializaMotores(new FileInputStream("TestesTxt/Teste.txt"));
        } catch (IOException e) {
            System.out.print("Arquivo 'teste.txt'não foi encontrado.\n");
            return;
        }

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

            ContadorTempo++;
        }

        for (List<Token> l:Memoria.Tokens) {
            for (Token t:l) {
                System.out.print(String.format("%s ",t.Token));
            }
            System.out.print("\n");
        }
    }
}
