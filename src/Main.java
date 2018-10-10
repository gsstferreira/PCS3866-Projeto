import Classes.Memoria;
import Classes.Simbolo;
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
            Nivel3.RealizarEvento(ContadorTempo);
            Nivel4.RealizarEvento(ContadorTempo);

            ContadorTempo++;
        }

        for (List<Simbolo> l:Memoria.Simbolos) {
            for (Simbolo s:l) {
                if(s.Simbolo != '\r' && s.Simbolo != '\n') {
                    System.out.print(String.format("%s ",s.Simbolo));
                }
            }
            System.out.print("\n");
        }
    }
}
