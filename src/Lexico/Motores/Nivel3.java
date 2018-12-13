package Lexico.Motores;

import Classes.AnalisadorLexico.Evento;
import Classes.Memoria;
import Classes.AnalisadorLexico.Caractere;
import Lexico.Metodos.CategorizadorCaractere;

public abstract class Nivel3 {

    private static int NumeroLinha = 0;
    private static int NumeroChar = 0;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.AcessoMotor(3).ObterProximoEvento(tempo);

        if(e.Tipo == Evento.LER_SIMBOLOS) {

            Caractere s = Memoria.Caracteres.get(NumeroLinha).get(NumeroChar);

            if(s != null) {

                NumeroChar++;
                CategorizadorCaractere.FiltragemSimbolo(s);

                Evento e2 = new Evento(Evento.LER_SIMBOLOS_UTEIS,tempo,tempo+1);
                ControleMotores.AcessoMotor(4).AdicionarEvento(e2);

                if(NumeroChar == Memoria.Caracteres.get(NumeroLinha).size()) {
                    NumeroLinha++;
                    NumeroChar = 0;
                }
            }
        }
    }
}
