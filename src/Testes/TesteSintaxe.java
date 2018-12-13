package Testes;

import Classes.AnalisadorSintatico.ResultadoAnalise;
import Classes.Memoria;
import Classes.Token;
import GeradorCodigo.Metodos.RPNtoMVN;
import Lexico.Metodos.CategorizadorToken;
import Metodos.Geral;
import Lexico.Motores.*;
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

        ControleSintaxe.InicializarAutomatos();
        System.out.println();
        System.out.println();

        List<Token> programa = new ArrayList<>();

        for (List<Token> lt:Memoria.TokensReclassificados) {
            programa.addAll(lt);
        }

        ResultadoAnalise a = ControleSintaxe.VerificarSintaxe(programa);

        if(a.Ok && a.linhaRestante.isEmpty()) {
            Geral.PrintOK("Análise Sintática OK");
        }
        else {
            int linha = a.linhaRestante.get(0).Linha;
            Geral.PrintErro(String.format("Linha %d: Erro de sintaxe: %s",linha + 1,Geral.FormatStringLinha(Memoria.Linhas.get(linha))));
        }


        List<Token> listaExp = new ArrayList<>();

        Token x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "a";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "b";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "c";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.OPERADOR;
        x.Token = "*";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "d";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "e";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.OPERADOR;
        x.Token = "*";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.OPERADOR;
        x.Token = "-";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "f";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.OPERADOR;
        x.Token = "*";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "g";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.OPERADOR;
        x.Token = "/";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.OPERADOR;
        x.Token = "-";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "h";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.IDENTIFICADOR;
        x.Token = "i";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.OPERADOR;
        x.Token = "/";
        listaExp.add(x);

        x = new Token();
        x.Tipo = Token.OPERADOR;
        x.Token = "+";
        listaExp.add(x);


        String s = RPNtoMVN.TransformarExpressaoRPN(listaExp);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(s);
    }
}
