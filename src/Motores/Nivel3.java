package Motores;

import Classes.Evento;
import Classes.Memoria;
import Classes.Simbolo;
import Metodos.CategorizadorCaractere;

public abstract class Nivel3 {

    private static int NumeroLinha = 0;
    private static int NumeroChar = 0;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.MotorNivel_3.ObterProximoEvento(tempo);

        if(e.Tipo == Evento.EV_LER_SIMBOLOS) {

            Simbolo s = Memoria.Simbolos.get(NumeroLinha).get(NumeroChar);

            if(s != null) {

                NumeroChar++;
                CategorizadorCaractere.FiltragemSimbolo(s);

                Evento e2 = new Evento(Evento.EV_LER_SIMBOLOS_UTEIS,tempo,tempo+1);
                ControleMotores.MotorNivel_4.AdicionarEvento(e2);

                if(NumeroChar == Memoria.Simbolos.get(NumeroLinha).size()) {
                    NumeroLinha++;
                    NumeroChar = 0;
                }
            }
        }
    }
}
