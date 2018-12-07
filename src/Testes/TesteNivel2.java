package Testes;

import Classes.Memoria;
import Classes.Caractere;
import Metodos.Geral;
import Motores.ControleMotores;
import Motores.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TesteNivel2 {

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

        // execução dos eventos de nível de abstração 1 e 2
        while (ControleMotores.ContinuarAnalise(1,2)) {

            Nivel1.RealizarEvento(contadorTempo);   // nível 1
            if(ControleMotores.ErroDeLeitura) { break; }

            Nivel2.RealizarEvento(contadorTempo);   // nível 2
            if(ControleMotores.ErroDeLeitura) { break; }

            contadorTempo++;    // incrementa passo
        }

        if(ControleMotores.ErroDeLeitura) {
            Geral.PrintErro();
            return;
        }

        int i = 0;

        for(List<Caractere> linha:Memoria.Caracteres){   // imprime caracteres de cada linha

            System.out.print(String.format("Caracteres da linha %d: ",i));

            for(Caractere s:linha){
                Geral.PrintSimbolos(s);
            }

            System.out.print("\n");
            i++;
        }
    }
}