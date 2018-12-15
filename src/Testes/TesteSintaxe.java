package Testes;

import Classes.AnalisadorSintatico.ResultadoAnalise;
import Classes.Memoria;
import Classes.Token;
import GeradorCodigo.Metodos.RPNtoMVN;
import Lexico.Metodos.CategorizadorToken;
import Metodos.Geral;
import Lexico.Motores.*;
import Sintaxe.AdicaoRedundancia;
import Sintaxe.ControleSintaxe;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TesteSintaxe {

    public static void main(String[] args) {

        Memoria.InicializarMemoria();   // inicialização da memória
        int contadorTempo = 0;          // inicialização do contador de passos

        try {
            // busca ponteiro para arquivo com código e inicializa os motores de evento
            ControleMotores.InicializaMotores(new FileInputStream("TestesTxt/TesteSintaxe.txt"));
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
            Geral.PrintErro(ControleMotores.DescricaoErro);
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
            Geral.PrintErro(ControleMotores.DescricaoErro);
            return;
        }

        for (List<Token> l:Memoria.TokensReclassificados) {
            for (Token t:l) {
                Geral.PrintToken(t);
            }
            System.out.println();
        }


        boolean sintatica = ControleSintaxe.AnaliseSintatica();

        if(!sintatica) {
            System.out.println();
            System.out.println();
            System.out.println();
            Geral.PrintErro(ControleSintaxe.DescricaoErro);
        }

    }
}
