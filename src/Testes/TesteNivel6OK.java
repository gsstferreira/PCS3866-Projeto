package Testes;

import Classes.AnalisadorSintatico.Automatos.EXP;
import Classes.AnalisadorSintatico.ResultadoAnalise;
import Classes.Memoria;
import Classes.Token;
import Metodos.CategorizadorToken;
import Metodos.Geral;
import Motores.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TesteNivel6OK {

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

        // execução dos eventos de nível de abstração 1 a 5
        while (ControleMotores.ContinuarAnalise(1,5)) {

            Nivel1.RealizarEvento(contadorTempo);   // nível 1
            if(ControleMotores.ErroDeLeitura) { break; }

            Nivel2.RealizarEvento(contadorTempo);   // nível 2
            if(ControleMotores.ErroDeLeitura) { break; }

            Nivel3.RealizarEvento(contadorTempo);   // nível 3
            if(ControleMotores.ErroDeLeitura) { break; }

            Nivel4.RealizarEvento(contadorTempo);   // nível 4
            if(ControleMotores.ErroDeLeitura) { break; }

            Nivel5.RealizarEvento(contadorTempo);   // nível 5
            if(ControleMotores.ErroDeLeitura) { break; }

            contadorTempo++;    // incrementa passo
        }

        if(ControleMotores.ErroDeLeitura) {
            Geral.PrintErro();
            return;
        }

        // incializa listas de tokens válidos
        CategorizadorToken.InicializaRecategorizador();

        // execução dos eventos de nível de abstração 6
        while(ControleMotores.ContinuarAnalise(6,6)) {

            Nivel6.RealizarEvento(contadorTempo);   // nível 6
            if(ControleMotores.ErroDeLeitura) { break; }

            contadorTempo++;    // incrementa passo
        }

        if(ControleMotores.ErroDeLeitura) {
            Geral.PrintErro();
            return;
        }

        for (List<Token> l:Memoria.TokensReclassificados) {
            for (Token t:l) {
                Geral.PrintToken(t);
            }
            System.out.println();
        }

        EXP.InicializarAutomato();


        List<Token> analisar = Memoria.TokensReclassificados.get(1);
        analisar.remove(0);
        analisar.remove(0);
        analisar.remove(0);

        ResultadoAnalise a = EXP.automato.ExecutarAutomato(analisar);

        System.out.println();

        if(a.Ok) {
            System.out.println("Sucesso linha");
        }
        else {
            System.out.println("Falha linha");
            for (Token t:a.linhaRestante) {
                Geral.PrintToken(t);
            }
        }

    }
}
