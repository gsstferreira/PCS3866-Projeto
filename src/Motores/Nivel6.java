package Motores;

import Classes.Evento;
import Classes.Memoria;
import Classes.Simbolo;
import Classes.Token;

import java.util.ArrayList;

public abstract class Nivel6 {

    private static int NumeroLinha = 0;
    private static int NumeroToken = 0;
    private static StringBuilder TokenBuilder = new StringBuilder();

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.MotorNivel_5.ObterProximoEvento(tempo);

        if(e.Tipo == Evento.EV_RECLASSIFICAR_TOKENS) {

            if(Memoria.TokensReclassificados.size() < NumeroLinha + 1) {
                Memoria.TokensReclassificados.add(new ArrayList<>());
            }



        }
    }
}
