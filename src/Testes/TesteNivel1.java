package Testes;

import Classes.Memoria;
import Classes.Token;
import Metodos.CategorizadorToken;
import Metodos.Geral;
import Motores.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TesteNivel1 {

    public static void main(String[] args) {

        Memoria.InicializarMemoria();   // inicialização da memória
        int contadorTempo = 0;          // inicialização do contador de passos

        try {
            // busca ponteiro para arquivo com código e inicializa os motores de evento
            ControleMotores.InicializaMotores(new FileInputStream("TestesTxt/TesteOK.txt"));
        } catch (IOException e) {
            // arquivo não encontrado
            System.out.print("Arquivo 'teste.txt'não foi encontrado.\n");
            return;
        }

        if(ControleMotores.ErroDeLeitura) {
            Geral.PrintErro();
            return;
        }

        // execução dos eventos de nível de abstração 1
        while (ControleMotores.ContinuarAnalise(1,1)) {

            Nivel1.RealizarEvento(contadorTempo);   // nível 1
            if(ControleMotores.ErroDeLeitura) { break; }

            contadorTempo++;    // incrementa passo
        }

        int i = 0;

        for(String linha:Memoria.Linhas){   // imprime linhas separadas

            System.out.print(String.format("Linha %2d: %s",i,linha));
            i++;
        }
    }
}
