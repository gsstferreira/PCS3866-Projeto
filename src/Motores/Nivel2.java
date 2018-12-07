package Motores;

import Classes.Evento;
import Classes.Memoria;
import Classes.Caractere;

import java.util.ArrayList;
import java.util.List;

public abstract class Nivel2 {

    private static int NumeroLinha = 0;
    private static int NumeroChar = 0;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.AcessoMotor(2).ObterProximoEvento(tempo);

        if(e.Tipo == Evento.LER_LINHA) {

            String linha = Memoria.Linhas.get(NumeroLinha);

            if(linha != null) {

                if(NumeroChar == 0) {
                    List<Caractere> simbolos = new ArrayList<>();
                    Memoria.Caracteres.add(simbolos);
                }

                char c = linha.charAt(NumeroChar);
                NumeroChar++;

                Caractere s = new Caractere();
                s.Caractere = c;

                Memoria.Caracteres.get(NumeroLinha).add(s);

                Evento e2 = new Evento(Evento.LER_SIMBOLOS,tempo,tempo+1);
                ControleMotores.AcessoMotor(3).AdicionarEvento(e2);

                if(NumeroChar == linha.length()) {
                    NumeroLinha++;
                    NumeroChar = 0;
                }
            }
        }
    }
}
