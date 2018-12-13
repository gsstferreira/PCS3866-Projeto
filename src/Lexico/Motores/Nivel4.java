package Lexico.Motores;

import Classes.AnalisadorLexico.Evento;
import Classes.Memoria;
import Classes.AnalisadorLexico.Caractere;
import Lexico.Metodos.CategorizadorCaractere;

public abstract class Nivel4 {

    private static int NumeroLinha = 0;
    private static int NumeroChar = 0;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.AcessoMotor(4).ObterProximoEvento(tempo);

        if(e.Tipo == Evento.LER_SIMBOLOS_UTEIS) {

            Caractere s = Memoria.Caracteres.get(NumeroLinha).get(NumeroChar);

            if(s != null) {

                NumeroChar++;
                CategorizadorCaractere.IdentificarSimbolo(s);

                if(NumeroChar == Memoria.Caracteres.get(NumeroLinha).size()) {
                    NumeroLinha++;
                    NumeroChar = 0;
                }

                Evento e2 = new Evento(Evento.CLASSIFICAR_TOKENS,tempo,tempo+1);
                ControleMotores.AcessoMotor(5).AdicionarEvento(e2);
            }
        }
    }
}
