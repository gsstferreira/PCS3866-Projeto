package Motores;

import Classes.Evento;
import Classes.Memoria;
import Classes.Simbolo;

import java.util.ArrayList;
import java.util.List;

public abstract class Nivel2 {

    private static int NumeroLinha = 0;
    private static int NumeroChar = 0;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.MotorNivel_2.ObterProximoEvento(tempo);

        if(e.Tipo == Evento.EV_LER_LINHA) {

            String linha = Memoria.Linhas.get(NumeroLinha);

            if(linha != null) {

                if(NumeroChar == 0) {
                    List<Simbolo> simbolos = new ArrayList<>();
                    Memoria.Simbolos.add(simbolos);
                }

                char c = linha.charAt(NumeroChar);
                NumeroChar++;

                Simbolo s = new Simbolo();
                s.Simbolo = c;

                Memoria.Simbolos.get(NumeroLinha).add(s);

                Evento e2 = new Evento(Evento.EV_LER_SIMBOLOS,tempo,tempo+1);
                ControleMotores.MotorNivel_3.AdicionarEvento(e2);

                if(NumeroChar == linha.length()) {
                    NumeroLinha++;
                    NumeroChar = 0;
                }
            }
        }
    }
}
